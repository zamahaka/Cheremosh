package org.zamahaka.cheremosh.domain.filedownload

import org.zamahaka.cheremosh.model.NoteFile

class NotesFilesDownloader(
        private val downloader: FilesDownloader
) {

    fun downloadFile(notes: NoteFile) = downloader.downloadFile(notes.file)

    fun cancelFile(notes: NoteFile) = downloader.cancelFile(uri = notes.file)

}

