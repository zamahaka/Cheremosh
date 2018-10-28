package org.zamahaka.cheremosh.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.persist.RealFilePersist
import org.zamahaka.cheremosh.ui.notes.NotesListViewModel

val viewModelsModule = module {
    viewModel {
        NotesListViewModel(
                fileOpener = get(),
                filesDataSource = get(),
                filesDownloader = get(),
                filePersist = RealFilePersist(
                        context = get(),
                        directory = "notes/"
                )
        )
    }
}