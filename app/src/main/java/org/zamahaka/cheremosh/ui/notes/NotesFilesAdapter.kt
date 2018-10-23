package org.zamahaka.cheremosh.ui.notes

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import org.zamahaka.cheremosh.ui.rv.IdEntityRvAdapter

class NotesFilesAdapter(
        private val onClick: (NotesFileListData) -> Unit,
        private val onCancel: (NotesFileListData) -> Unit
) : IdEntityRvAdapter<NotesFileListData, NoteFileViewHolder>(
        object : DiffUtil.ItemCallback<NotesFileListData>() {
            override fun areItemsTheSame(oldItem: NotesFileListData, newItem: NotesFileListData): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: NotesFileListData, newItem: NotesFileListData): Boolean = oldItem == newItem
            override fun getChangePayload(oldItem: NotesFileListData, newItem: NotesFileListData): Any? = when {
                oldItem.progressing && newItem.progressing -> newItem.progress

                else -> null
            }
        }
) {

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
