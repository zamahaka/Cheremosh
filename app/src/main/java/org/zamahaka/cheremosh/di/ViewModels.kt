package org.zamahaka.cheremosh.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.zamahaka.cheremosh.ui.notes.NotesListViewModel

val viewModelsModule = module {
    viewModel { NotesListViewModel() }
}