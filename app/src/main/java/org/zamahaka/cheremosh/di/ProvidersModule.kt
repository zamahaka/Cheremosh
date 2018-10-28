package org.zamahaka.cheremosh.di

import org.koin.dsl.module.module
import org.zamahaka.cheremosh.domain.persist.FileOpener

val providersModule = module {
    single { FileOpener(context = get()) }
}