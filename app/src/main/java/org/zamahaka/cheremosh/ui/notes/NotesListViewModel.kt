package org.zamahaka.cheremosh.ui.notes

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.filedownload.NotesFilesDownloader
import org.zamahaka.cheremosh.domain.progressPercentageOf
import java.io.File

class NotesListViewModel(
        private val context: Context,
        private val filesDataSource: NotesFilesDataSource,
        private val filesDownloader: NotesFilesDownloader
) : ViewModel() {

    val notesFiles = MediatorLiveData<List<NotesFileListData>>()
            .apply {
                addSource(filesDataSource.notes) {
                    value = it.orEmpty().map { file -> NotesFileListData(file) }
                }
            }


    fun fileSelected(file: NotesFileListData) {
        filesDownloader.downloadFile(file.notesFile).apply {
            onSuccess { openPdf(it) }

            onProgress { current, total ->
                updateFileListData(file.copy(
                        progressing = true,
                        progress = progressPercentageOf(current, total)
                ))
            }

            onComplete { updateFileListData(file.copy(progressing = false, progress = 0)) }

            onCancel { context.toast("Canceled") }

            onFailure { context.toast("Failure ${it.message}") }
        }
    }

    fun fileCanceled(file: NotesFileListData) = filesDownloader.cancelFile(file.notesFile)


    private fun openPdf(file: File) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            setDataAndType(
                    FileProvider.getUriForFile(context, context.applicationContext.packageName, file),
                    "application/pdf"
            )
            newTask()
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else context.toast("Not pdf activity found")
    }

    private fun updateFileListData(data: NotesFileListData) {
        val currentList = notesFiles.value ?: return
        val fileToChange = currentList.find { it.id == data.id } ?: return

        notesFiles.value = currentList.toMutableList().apply {
            set(indexOf(fileToChange), data)
        }
    }

}