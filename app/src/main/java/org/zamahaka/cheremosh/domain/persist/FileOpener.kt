package org.zamahaka.cheremosh.domain.persist

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import org.jetbrains.anko.newTask
import org.jetbrains.anko.noHistory
import org.jetbrains.anko.toast
import java.io.File

class FileOpener(
        private val context: Context
) {

    fun openPdf(file: File) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(
                    FileProvider.getUriForFile(context, context.applicationContext.packageName, file),
                    getType(`for` = file)
            )
            newTask()
            noHistory()
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else context.toast("No pdf activity found")
    }


    private fun getType(`for`: File): String {
        return when (`for`.extension) {
            "doc", "docx" -> "application/msword"
            "pdf" -> "application/pdf"
            "ppt", "pptx" -> "application/vnd.ms-powerpoint"
            "xls", "xlsx" -> "application/vnd.ms-excel"
            "zip", "rar" -> "application/x-wav"
            "rft" -> "application/rft"
            "wav", "mp3" -> "audio/x-wav"
            "gif" -> "image/gif"
            "jpg", "jpeg", "png" -> "application/jpeg"
            "txt" -> "text/plain"
            "3gp", "mpg", "mpeg", "mpe", "mp4", "avi" -> "video/*"

            else -> "*/*"
        }
    }

}