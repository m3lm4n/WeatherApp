package com.lastowski.weatherapp.common

import android.app.Application
import com.lastowski.weatherapp.BuildConfig
import com.lastowski.weatherapp.data.WeatherApiService
import com.lastowski.weatherapp.data.WeatherRepository
import com.lastowski.weatherapp.location.LocationProvider
import com.lastowski.weatherapp.permissions.PermissionProvider
import com.lastowski.weatherapp.permissions.PermissionRequestListener
import com.lastowski.weatherapp.view.widget.view.WeatherIconPathProvider
import com.lastowski.weatherapp.view.widget.view.WidgetPendingIntentProvider
import com.lastowski.weatherapp.view.widget.view.WidgetViewBuilder
import com.lastowski.weatherapp.view.widget.view.WidgetViewStateMapper
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
            single { LocationProvider(androidContext(), get()) }
            single { WeatherApiService.create() }
            single { WeatherRepository(get(), get()) }
            factory<PermissionRequestListener> { get(PermissionProvider::class) }

            viewModel { ConfigurationViewModel(get()) }

            factory { WidgetPendingIntentProvider() }
            factory { WeatherIconPathProvider() }
            factory { WidgetViewBuilder(get()) }
            factory { WidgetViewStateMapper(get()) }
        }

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }

        registerActivityLifecycleCallbacks(getKoin().get<PermissionProvider>())
    }
}


