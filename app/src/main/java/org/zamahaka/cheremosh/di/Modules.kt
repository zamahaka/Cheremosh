package org.zamahaka.cheremosh.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.repository.ConcertRepository
import org.zamahaka.cheremosh.domain.repository.datasource.remote.ConcertRemoteDataSource
import org.zamahaka.cheremosh.ui.concert.ConcertViewModel

val module = module {
    single { FirebaseDatabase.getInstance() }
    single { ConcertRemoteDataSource(get()) }
    single { ConcertRepository(get()) }

    viewModel { ConcertViewModel(get(), get()) }
}

val appModules = listOf(module, viewModelsModule)