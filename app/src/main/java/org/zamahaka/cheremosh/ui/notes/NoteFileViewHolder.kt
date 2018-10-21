package org.zamahaka.cheremosh.ui.notes

import android.os.Build
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note_file.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.extensions.gone
import org.zamahaka.cheremosh.extensions.onClick
import org.zamahaka.cheremosh.extensions.visible
import org.zamahaka.cheremosh.ui.rv.BaseRvViewHolder
import org.zamahaka.cheremosh.ui.rv.BindViewHolder
import org.zamahaka.cheremosh.ui.rv.ViewHolderViewCreator

class NoteFileViewHolder private constructor(
        view: View,
        onClick: (position: Int) -> Unit,
        onCancel: (position: Int) -> Unit
) : BaseRvViewHolder(view), BindViewHolder<NotesFileListData> {

    init {
        onClick(view, onClick)
        onClick(btnCancel, onCancel)
    }


    override fun bind(data: NotesFileListData) {
        txtName.text = data.notesFile.name

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progress.setProgress(data.progress, true)
        } else progress.progress = data.progress

        if (data.progressing) groupProgress.visible() else groupProgress.gone()
    }


    companion object : ViewHolderViewCreator {
        override val layoutRes: Int get() = R.layout.item_note_file

        operator fun invoke(
                parent: ViewGroup,
                onClick: (position: Int) -> Unit,
                onCancel: (position: Int) -> Unit
        ) = NoteFileViewHolder(
                view = inflate(parent), onClick = onClick, onCancel = onCancel
        )
    }

}
