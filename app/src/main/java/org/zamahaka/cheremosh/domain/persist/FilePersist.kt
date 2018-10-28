package org.zamahaka.cheremosh.domain.persist

import android.content.Context
import java.io.File

interface FilePersist {

    fun createFile(name: String): File

    fun hasFile(name: String): Boolean

    fun getFile(name: String): File

    fun deleteFile(name: String)

}

class RealFilePersist(
        context: Context,
        directory: String
) : FilePersist {


    private val filesDir = File(context.filesDir, directory).apply { mkdirs() }
    private val fileList get() = filesDir.listFiles()


    override fun createFile(name: String): File = File(filesDir, name)

    override fun hasFile(name: String): Boolean = fileList.first { it.name == name } != null

    override fun getFile(name: String): File = createFile(name = name)

    override fun deleteFile(name: String) {
        val file = createFile(name)

        if (file.exists()) file.delete()
    }

}