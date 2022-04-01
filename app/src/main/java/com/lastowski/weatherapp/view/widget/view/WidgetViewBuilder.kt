package com.lastowski.weatherapp.view.widget.view

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import com.lastowski.weatherapp.R

class WidgetViewBuilder(
    private val pendingIntentProvider: WidgetPendingIntentProvider
) {

    fun buildRemoteViews(context: Context, appWidgetId: Int, viewState: WidgetViewState): RemoteViews = when(viewState) {
        is WidgetViewState.PermissionPrompt -> buildNoPermissionViews(context)
        is WidgetViewState.CurrentWeather -> buildBasicWidgetViews(context, viewState, appWidgetId)
    }

    private fun buildBasicWidgetViews(
        context: Context,
        viewState: WidgetViewState.CurrentWeather,
        appWidgetId: Int
    ): RemoteViews {
        val views = RemoteViews(
            context.packageName,
            R.layout.widget_basic
        )

        views.setTextViewText(
            R.id.temperature_text,
            viewState.temperature
        )
        views.setTextViewText(R.id.city_name, viewState.locationName)

        val bitmap =
            BitmapFactory.decodeStream(context.assets.open(viewState.iconPath))
        views.setImageViewBitmap(R.id.weather_icon, bitmap)

        views.setOnClickPendingIntent(
            R.id.update_button,
            pendingIntentProvider.buildUpdatePendingIntent(context, appWidgetId)
        )

        return views
    }

    private fun buildNoPermissionViews(context: Context): RemoteViews {
        val views = RemoteViews(
            context.packageName,
            R.layout.widget_needs_permission
        )

        views.setOnClickPendingIntent(
            R.id.needs_permission_button,
            pendingIntentProvider.buildConfigurationActivityIntent(context)
        )
        return views
    }
}