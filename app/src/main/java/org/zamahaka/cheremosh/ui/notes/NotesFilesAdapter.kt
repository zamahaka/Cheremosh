package org.zamahaka.cheremosh.ui.notes

import android.view.ViewGroup
import org.zamahaka.cheremosh.ui.rv.IdEntityRvAdapter

class NotesFilesAdapter(
        private val onClick: (NotesFileListData) -> Unit,
        private val onCancel: (NotesFileListData) -> Unit
) : IdEntityRvAdapter<NotesFileListData, NoteFileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteFileViewHolder(
                    parent = parent,
                    onClick = { onClick(getItem(it)) },
                    onCancel = { onCancel(getItem(it)) }
            )

    override fun onBindViewHolder(holder: NoteFileViewHolder, position: Int) =
            holder.bind(getItem(position))

}
