package com.lastowski.weatherapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class WeatherAppWidgetProvider: AppWidgetProvider() {

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