package com.lastowski.weatherapp.view.widget.view

sealed class WidgetViewState {
    object PermissionPrompt: WidgetViewState()
    data class CurrentWeather(
        val temperature: String,
        val locationName: String,
        val iconPath: String
    ): WidgetViewState()
}