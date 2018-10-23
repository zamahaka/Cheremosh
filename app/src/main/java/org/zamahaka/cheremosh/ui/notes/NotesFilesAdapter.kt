package org.zamahaka.cheremosh.ui.notes

import android.view.ViewGroup
import org.zamahaka.cheremosh.ui.rv.IdEntityRvAdapter

class NotesFilesAdapter(
        private val onClick: (NotesFileListData) -> Unit,
        private val onCancel: (NotesFileListData) -> Unit
) : IdEntityRvAdapter<NotesFileListData, NoteFileViewHolder>(IdEntityCallback { old, new ->
    when {
        old.progressing && new.progressing -> new.progress

        else -> null
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NoteFileViewHolder(
                    parent = parent,
                    onClick = { onClick(getItem(it)) },
                    onCancel = { onCancel(getItem(it)) }
            )

    override fun onBindViewHolder(holder: NoteFileViewHolder, position: Int) =
            holder.bind(getItem(position))

    override fun onBindViewHolder(
            holder: NoteFileViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder = holder, position = position) else {
            payloads.firstOrNull { it is Int }?.let { it as Int }?.let(holder::updateProgress)
        }
    }

}
