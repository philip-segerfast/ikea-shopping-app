package dev.psegerfast.ikeashoppingapp

import android.app.Application
import dev.psegerfast.ikeashoppingapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class IkeaShoppingApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start logging
        Timber.plant(Timber.DebugTree())

        // Setup koin
        startKoin {
            androidLogger()
            androidContext(this@IkeaShoppingApplication)
            modules(appModule)
        }
    }
}