package org.zamahaka.cheremosh.ui.notes

import android.view.ViewGroup
import org.zamahaka.cheremosh.model.NoteFile
import org.zamahaka.cheremosh.model.NoteFileId
import org.zamahaka.cheremosh.ui.rv.IdEntityRvAdapter

class NotesFilesAdapter(
        private val onClick: (NoteFileId) -> Unit
) : IdEntityRvAdapter<NoteFile, NoteFileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteFileViewHolder(parent = parent, onClick = { onClick(getItem(it).id) })

    override fun onBindViewHolder(holder: NoteFileViewHolder, position: Int) =
            holder.bind(getItem(position))

}
