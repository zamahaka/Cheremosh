package org.zamahaka.cheremosh.di

import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSourceImpl
import org.zamahaka.cheremosh.domain.filedownload.FilesDownloader
import org.zamahaka.cheremosh.domain.filedownload.NotesFilesDownloader

val dataSourceModule = module {

    single<NotesFilesDataSource> { NotesFilesDataSourceImpl() }

    single { FilesDownloader(context = get()) }
    single { NotesFilesDownloader(downloader = get()) }

}