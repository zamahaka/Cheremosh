package org.zamahaka.cheremosh.ui.notes

import org.zamahaka.cheremosh.model.IdEntity
import org.zamahaka.cheremosh.model.NoteFile
import org.zamahaka.cheremosh.model.NoteFileId

data class NotesFileListData(
        val notesFile: NoteFile,
        val status: NoteFileStatus
) : IdEntity<NoteFileId> {
    override val id: NoteFileId get() = notesFile.id
}

sealed class NoteFileStatus {
    object Downloaded : NoteFileStatus()
    object NotDownloaded : NoteFileStatus()
    class Downloading(val progress: Int) : NoteFileStatus()
}


val NotesFileListData.isDownloading get() = status is NoteFileStatus.Downloading