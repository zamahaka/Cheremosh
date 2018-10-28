package org.zamahaka.cheremosh.ui.notes

import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.filedownload.FirebaseFilesDownloader
import org.zamahaka.cheremosh.domain.persist.FileOpener
import org.zamahaka.cheremosh.domain.persist.FilePersist
import org.zamahaka.cheremosh.domain.progressPercentage
import org.zamahaka.cheremosh.extensions.asMutable
import org.zamahaka.cheremosh.extensions.map
import org.zamahaka.cheremosh.model.NoteFile

class NotesListViewModel(
        private val fileOpener: FileOpener,
        private val filesDataSource: NotesFilesDataSource,
        private val filesDownloader: FirebaseFilesDownloader,
        private val filePersist: FilePersist
) : ViewModel() {

    val notesFiles = filesDataSource.notes.map {
        it.map { notes ->
            NotesFileListData(
                    notesFile = notes,
                    status = when {
                        filePersist.hasFile(getFileName(notes)) -> NoteFileStatus.Downloaded

                        filesDownloader.isDownloading(notes.file) -> NoteFileStatus.Downloading(
                                filesDownloader.getTask(notes.file)?.run {
                                    progressPercentage(of = current, from = total)
                                } ?: 0
                        )

                        else -> NoteFileStatus.NotDownloaded
                    }
            )
        }
    }.asMutable()


    fun fileSelected(file: NotesFileListData) {
        val fileName = getFileName(file.notesFile)

        when {
            filesDownloader.isDownloading(file.notesFile.file) -> {
            }
            filePersist.hasFile(fileName) -> fileOpener.openPdf(filePersist.getFile(name = fileName))
            !filesDownloader.isDownloading(file.notesFile.file) -> download(file)
        }
    }

    fun download(file: NotesFileListData) {
        val fileUri = file.notesFile.file

        filesDownloader.download(
                uri = fileUri,
                into = filePersist.createFile(
                        name = getFileName(file.notesFile)
                )
        ).apply {
            onSuccess {
                updateFileListData(file.copy(status = NoteFileStatus.Downloaded))
            }

            onProgress { current, total ->
                updateFileListData(file.copy(
                        status = NoteFileStatus.Downloading(
                                progress = progressPercentage(of = current, from = total)
                        )
                ))
            }

            onCancel { updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded)) }

            onFailure {
                updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded))
            }
        }
    }

    fun cancelDownload(file: NotesFileListData) {
        filesDownloader.cancelFileDownload(uri = file.notesFile.file)

        updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded))
    }

    fun delete(file: NotesFileListData) {
        filePersist.deleteFile(
                name = FirebaseStorage.getInstance().getReferenceFromUrl(
                        file.notesFile.file
                ).name
        )

        updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded))
    }


    private fun getFileName(notes: NoteFile): String = FirebaseStorage.getInstance()
            .getReferenceFromUrl(notes.file).name

    private fun updateFileListData(data: NotesFileListData) {
        val currentList = notesFiles.value ?: return
        val fileToChange = currentList.find { it.id == data.id } ?: return

        notesFiles.value = currentList.toMutableList().apply {
            set(indexOf(fileToChange), data)
        }
    }

}