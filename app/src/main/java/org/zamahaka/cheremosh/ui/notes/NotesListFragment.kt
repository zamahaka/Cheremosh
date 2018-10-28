package org.zamahaka.cheremosh.ui.notes

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.extensions.observe
import org.zamahaka.cheremosh.ui.base.BaseFragment

class NotesListFragment : BaseFragment() {

    override val layoutRes: Int get() = R.layout.fragment_notes


    private val viewModel by viewModel<NotesListViewModel>()


    private val notesFilesAdapter by lazy {
        NotesFilesAdapter(
                onClick = viewModel::fileSelected,
                onDownload = viewModel::download,
                onCancel = viewModel::cancelDownload,
                onDelete = viewModel::delete
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNotes.apply { adapter = notesFilesAdapter }

        viewModel.notesFiles.observe(viewLifecycleOwner) {
            notesFilesAdapter.submitList(it.orEmpty())
        }
    }

}