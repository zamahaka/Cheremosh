package org.zamahaka.cheremosh.domain.filedownload

import com.google.firebase.storage.FirebaseStorage
import java.io.File

class FirebaseFilesDownloader {

    private val firebaseStorage = FirebaseStorage.getInstance()

    private val activeTasks =
            mutableMapOf<String, FileDownloadTask>()

    fun isDownloading(uri: String) = activeTasks.containsKey(uri)

    fun getTask(forUri: String): FileDownloadTask? = activeTasks[forUri]

    fun download(uri: String, into: File): FileDownloadTask = activeTasks[uri] ?: run {
        val fileRef = firebaseStorage.getReferenceFromUrl(uri)

        FirebaseFileDownloadTask(
                fileReference = fileRef, fileToSaveIn = into
        ).apply { onComplete { activeTasks.remove(uri) } }.also { activeTasks[uri] = it }
    }

    fun cancelFileDownload(uri: String) {
        activeTasks[uri]?.cancel()
    }

}