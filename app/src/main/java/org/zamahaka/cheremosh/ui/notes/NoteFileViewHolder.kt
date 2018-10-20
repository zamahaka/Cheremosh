package org.zamahaka.cheremosh.ui.notes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note_file.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.extensions.onClick
import org.zamahaka.cheremosh.model.NoteFile
import org.zamahaka.cheremosh.ui.rv.BindViewHolder
import org.zamahaka.cheremosh.ui.rv.ViewHolderViewCreator

class NoteFileViewHolder private constructor(
        override val containerView: View,
        onClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(containerView), BindViewHolder<NoteFile>, LayoutContainer {


    init {
        onClick(containerView, onClick)
    }


    override fun bind(data: NoteFile) {
        txtName.text = data.name
    }


    companion object : ViewHolderViewCreator {
        override val layoutRes: Int get() = R.layout.item_note_file

        operator fun invoke(parent: ViewGroup, onClick: (position: Int) -> Unit) =
                NoteFileViewHolder(containerView = inflate(parent), onClick = onClick)
    }

}
