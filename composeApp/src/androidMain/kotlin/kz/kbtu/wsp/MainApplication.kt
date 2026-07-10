package kz.kbtu.wsp

import android.app.Application
import kz.kbtu.wsp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}