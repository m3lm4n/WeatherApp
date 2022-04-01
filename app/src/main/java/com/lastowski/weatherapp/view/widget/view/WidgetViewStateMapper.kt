package com.lastowski.weatherapp.view.widget.view

import com.lastowski.weatherapp.data.CurrentWeatherData

class WidgetViewStateMapper(
    private val iconPathProvider: WeatherIconPathProvider
) {
    fun toViewState(hasPermission: Boolean, currentWeatherData: CurrentWeatherData?): WidgetViewState {
        return if (!hasPermission || currentWeatherData == null) {
            WidgetViewState.PermissionPrompt
        } else {
            WidgetViewState.CurrentWeather(
                temperature = "${currentWeatherData.current.temp_c.toInt()}°",
                locationName = currentWeatherData.location.name,
                iconPath = iconPathProvider.buildIconPath(currentWeatherData)
            )
        }
    }
}