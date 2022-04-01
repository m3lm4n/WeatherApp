package com.lastowski.weatherapp.data

import com.lastowski.weatherapp.location.LocationProvider

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val locationProvider: LocationProvider
){

    suspend fun getCurrentWeatherData(): CurrentWeatherData? {
        val location = locationProvider.getLocation() ?: return null

        val response = weatherApiService.getCurrentWeather(query = location.asQueryParameter)
        return response.body()
    }

    private val LocationProvider.Location.asQueryParameter: String get() = "${latitude},${longitude}"
}