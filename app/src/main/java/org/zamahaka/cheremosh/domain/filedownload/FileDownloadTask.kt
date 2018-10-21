package org.zamahaka.cheremosh.domain.filedownload

import com.google.firebase.storage.StorageReference
import java.io.File

class FileDownloadTask(
        fileReference: StorageReference,
        private val fileToSaveIn: File
) {

    private val firebaseTask = fileReference.getFile(fileToSaveIn)


    fun onSuccess(handler: (File) -> Unit) {
        firebaseTask.addOnSuccessListener { handler(fileToSaveIn) }
    }

    fun onFailure(handler: (Exception) -> Unit) {
        firebaseTask.addOnFailureListener(handler)
    }

    fun onComplete(handler: () -> Unit) {
        firebaseTask.addOnCompleteListener { handler() }
    }

    fun onCancel(handler: () -> Unit) {
        firebaseTask.addOnCanceledListener(handler)
    }

    fun onProgress(handler: (current: Long, full: Long) -> Unit) {
        firebaseTask.addOnProgressListener { handler(it.bytesTransferred, it.totalByteCount) }
    }


    fun cancel() {
        firebaseTask.cancel()
        if (fileToSaveIn.exists()) fileToSaveIn.delete()
    }

}