package org.zamahaka.cheremosh.di

import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSource
import org.zamahaka.cheremosh.domain.datasource.NotesFilesDataSourceImpl

val dataSourceModule = module {

    single<NotesFilesDataSource> { NotesFilesDataSourceImpl() }

}