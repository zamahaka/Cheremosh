package org.zamahaka.cheremosh.di

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import org.zamahaka.cheremosh.ui.notes.NotesListViewModel

val viewModelsModule = applicationContext {
    viewModel { NotesListViewModel() }
}