package org.zamahaka.cheremosh.domain.filedownload

import com.google.firebase.storage.StorageReference
import java.io.File

interface FileDownloadTask {

    val total: Long
    val current: Long

    fun onSuccess(handler: (File) -> Unit)
    fun onFailure(handler: (Exception) -> Unit)
    fun onComplete(handler: () -> Unit)
    fun onCancel(handler: () -> Unit)
    fun onProgress(handler: (current: Long, full: Long) -> Unit)

    fun cancel()

}

class FirebaseFileDownloadTask(
        fileReference: StorageReference,
        private val fileToSaveIn: File
) : FileDownloadTask {

    private val firebaseTask = fileReference.getFile(fileToSaveIn)


    override val total: Long get() = firebaseTask.snapshot.totalByteCount
    override val current: Long get() = firebaseTask.snapshot.bytesTransferred

    override fun onSuccess(handler: (File) -> Unit) {
        firebaseTask.addOnSuccessListener { handler(fileToSaveIn) }
    }

    override fun onFailure(handler: (Exception) -> Unit) {
        firebaseTask.addOnFailureListener(handler)
    }

    override fun onComplete(handler: () -> Unit) {
        firebaseTask.addOnCompleteListener { handler() }
    }

    override fun onCancel(handler: () -> Unit) {
        firebaseTask.addOnCanceledListener(handler)
    }

    override fun onProgress(handler: (current: Long, full: Long) -> Unit) {
        firebaseTask.addOnProgressListener { handler(it.bytesTransferred, it.totalByteCount) }
    }


    override fun cancel() {
        firebaseTask.cancel()
        if (fileToSaveIn.exists()) fileToSaveIn.delete()
    }

}