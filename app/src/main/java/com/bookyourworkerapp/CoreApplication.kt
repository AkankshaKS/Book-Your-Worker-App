package com.bookyourworkerapp

import android.app.Application
import com.bookyourworkerapp.di.dbModule
import com.bookyourworkerapp.di.repositoryModule
import com.bookyourworkerapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule,  uiModule))
        }
    }
}