package com.lastowski.weatherapp.view.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context

import com.lastowski.weatherapp.data.WeatherRepository
import com.lastowski.weatherapp.permissions.PermissionProvider
import com.lastowski.weatherapp.view.widget.view.WidgetViewBuilder
import com.lastowski.weatherapp.view.widget.view.WidgetViewStateMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext

class WeatherAppWidgetProvider : AppWidgetProvider() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val viewStateMapper: WidgetViewStateMapper by inject()
    private val weatherRepository: WeatherRepository by inject()
    private val permissionProvider: PermissionProvider by inject()
    private val viewBuilder: WidgetViewBuilder by inject()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val pendingResult = goAsync()

        scope.launch {
            appWidgetIds.forEach { appWidgetId ->
                val hasPermission =
                    permissionProvider.hasPermission(PermissionProvider.Permission.CoarseLocation)
                val currentWeatherData = weatherRepository.getCurrentWeatherData()
                val viewState = viewStateMapper.toViewState(hasPermission, currentWeatherData)
                val views = viewBuilder.buildRemoteViews(context, appWidgetId, viewState)

                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            pendingResult.finish()
        }
    }

    private inline fun <reified T : Any> inject() = lazy { GlobalContext.get().get<T>() }
}

