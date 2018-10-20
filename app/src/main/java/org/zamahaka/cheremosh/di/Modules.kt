package org.zamahaka.cheremosh.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import org.zamahaka.cheremosh.domain.repository.ConcertRepository
import org.zamahaka.cheremosh.domain.repository.datasource.remote.ConcertRemoteDataSource
import org.zamahaka.cheremosh.ui.concert.ConcertViewModel

val module = applicationContext {
    bean { FirebaseDatabase.getInstance() }
    bean { ConcertRemoteDataSource(get()) }
    bean { ConcertRepository(get()) }

    viewModel { ConcertViewModel(get(), get()) }
}

val appModules = listOf(module, viewModelsModule)