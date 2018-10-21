package org.zamahaka.cheremosh.domain.filedownload

import android.content.Context
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class FilesDownloader(
        private val context: Context
) {

    private val firebaseStorage = FirebaseStorage.getInstance()
    private val activeTasks = mutableMapOf<String, FileDownloadTask>()

    fun downloadFile(uri: String): FileDownloadTask = activeTasks[uri] ?: run {
        val fileRef = firebaseStorage.getReferenceFromUrl(uri)

        val notesDir = File(context.filesDir, "notes/").apply { mkdirs() }
        val file = File(notesDir, fileRef.name)

        FileDownloadTask(fileReference = fileRef, fileToSaveIn = file)
                .apply { onComplete { activeTasks.remove(uri) } }
                .also { activeTasks[uri] = it }
    }

    fun cancelFile(uri: String) {
        activeTasks[uri]?.cancel()
    }

}