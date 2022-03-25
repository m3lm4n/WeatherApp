package com.lastowski.weatherapp.view

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.lastowski.weatherapp.R

class WeatherAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->

            val views = RemoteViews(
                context.packageName,
                R.layout.widget_basic
            )

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}