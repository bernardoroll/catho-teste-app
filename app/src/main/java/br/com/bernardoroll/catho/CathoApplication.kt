package br.com.bernardoroll.catho

import android.app.Application
import br.com.bernardoroll.catho.di.Modules.cathoModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CathoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CathoApplication)
            modules(
                cathoModules
            )
        }
    }
}
