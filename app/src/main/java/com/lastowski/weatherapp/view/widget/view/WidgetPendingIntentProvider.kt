package com.lastowski.weatherapp.view.widget.view

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import com.lastowski.weatherapp.view.widget.WeatherAppWidgetProvider
import com.lastowski.weatherapp.view.widget.configuration.ConfigurationActivity

class WidgetPendingIntentProvider {

    fun buildUpdatePendingIntent(context: Context, appWidgetId: Int): PendingIntent {
        val intent = Intent(context, WeatherAppWidgetProvider::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_OLD_IDS, intArrayOf(appWidgetId))

        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
    }

    fun buildConfigurationActivityIntent(context: Context): PendingIntent {
        val intent = Intent(context, ConfigurationActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)
    }
}