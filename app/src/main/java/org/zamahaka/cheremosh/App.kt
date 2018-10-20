package org.zamahaka.cheremosh

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.android.startKoin
import org.zamahaka.cheremosh.di.appModules

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)
    }

}