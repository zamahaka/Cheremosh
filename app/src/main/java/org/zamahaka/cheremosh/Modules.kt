package org.zamahaka.cheremosh

import com.google.firebase.database.FirebaseDatabase
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import org.zamahaka.cheremosh.praktuka.ConcertViewModel
import org.zamahaka.cheremosh.praktuka.repository.ConcertRepository
import org.zamahaka.cheremosh.praktuka.repository.datasource.remote.ConcertRemoteDataSource

val module = applicationContext {
    bean { FirebaseDatabase.getInstance() }
    bean { ConcertRemoteDataSource(get()) }
    bean { ConcertRepository(get()) }

    viewModel { ConcertViewModel(get(), get()) }
}