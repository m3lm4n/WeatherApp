package com.lastowski.weatherapp.data

import com.lastowski.weatherapp.location.LocationProvider

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val locationProvider: LocationProvider
) {
    private val DEFAULT_QUERY = "Wroclaw"

    suspend fun getCurrentWeatherData(): CurrentWeatherData? {
        val location = locationProvider.getLocation()
        val query = location?.asQueryParameter ?: DEFAULT_QUERY

        val response = weatherApiService.getCurrentWeather(query = query)
        return response.body()
    }

    private val LocationProvider.Location.asQueryParameter: String get() = "${latitude},${longitude}"
}