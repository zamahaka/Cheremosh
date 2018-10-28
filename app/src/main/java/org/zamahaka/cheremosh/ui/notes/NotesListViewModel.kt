package org.zamahaka.cheremosh.ui.notes

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import org.jetbrains.anko.newTask
import org.jetbrains.anko.noHistory
import org.jetbrains.anko.toast
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.filedownload.FirebaseFilesDownloader
import org.zamahaka.cheremosh.domain.persist.FilePersist
import org.zamahaka.cheremosh.domain.progressPercentage
import org.zamahaka.cheremosh.extensions.asMutable
import org.zamahaka.cheremosh.extensions.map
import org.zamahaka.cheremosh.model.NoteFile
import java.io.File

class NotesListViewModel(
        private val context: Context,
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

        if (filePersist.hasFile(fileName)) {
            openPdf(filePersist.getFile(name = fileName))
        } else context.toast("Not implemented")
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

            onComplete { updateFileListData(file.copy(status = NoteFileStatus.Downloaded)) }

            onCancel { updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded)) }

            onFailure {
                updateFileListData(file.copy(status = NoteFileStatus.NotDownloaded))

                context.toast("Failure ${it.message}")
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

    private fun openPdf(file: File) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(
                    FileProvider.getUriForFile(context, context.applicationContext.packageName, file),
                    "application/pdf"
            )
            newTask()
            noHistory()
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else context.toast("No pdf activity found")
    }

    private fun updateFileListData(data: NotesFileListData) {
        val currentList = notesFiles.value ?: return
        val fileToChange = currentList.find { it.id == data.id } ?: return

        notesFiles.value = currentList.toMutableList().apply {
            set(indexOf(fileToChange), data)
        }
    }

}