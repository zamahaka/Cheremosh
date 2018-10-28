package org.zamahaka.cheremosh.di

import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSourceImpl
import org.zamahaka.cheremosh.domain.filedownload.FirebaseFilesDownloader

val dataSourceModule = module {

    single<NotesFilesDataSource> { NotesFilesDataSourceImpl() }

    single { FirebaseFilesDownloader() }

}