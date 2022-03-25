package com.lastowski.weatherapp.common

import android.app.Application
import com.lastowski.weatherapp.BuildConfig
import com.lastowski.weatherapp.permssions.PermissionProvider
import com.lastowski.weatherapp.permssions.PermissionRequestListener
import com.lastowski.weatherapp.view.model.ConfigurationViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { PermissionProvider(androidContext()) }
            factory<PermissionRequestListener> { get(PermissionProvider::class) }

            viewModel { ConfigurationViewModel(get()) }
        }

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }

        registerActivityLifecycleCallbacks(getKoin().get<PermissionProvider>())
    }
}


