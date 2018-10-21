package org.zamahaka.cheremosh.ui.notes

import org.zamahaka.cheremosh.model.IdEntity
import org.zamahaka.cheremosh.model.NoteFile
import org.zamahaka.cheremosh.model.NoteFileId

data class NotesFileListData(
        val notesFile: NoteFile,
        val progressing: Boolean = false,
        val progress: Int = 0
) : IdEntity<NoteFileId> {
    override val id: NoteFileId get() = notesFile.id
}