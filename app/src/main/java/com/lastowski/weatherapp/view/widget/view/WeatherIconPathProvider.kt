package com.lastowski.weatherapp.view.widget.view

import android.net.Uri
import com.lastowski.weatherapp.data.CurrentWeatherData

class WeatherIconPathProvider {
    private val DAY_ICONS_FOLDER_NAME = "day"
    private val NIGHT_ICONS_FOLDER_NAME = "night"

    fun buildIconPath(currentWeatherData: CurrentWeatherData): String {
        val fileName = Uri.parse(currentWeatherData.current.condition.icon).lastPathSegment
        val daySegment = if (currentWeatherData.current.is_day == 1) DAY_ICONS_FOLDER_NAME else NIGHT_ICONS_FOLDER_NAME

        return "$daySegment/$fileName"
    }
}