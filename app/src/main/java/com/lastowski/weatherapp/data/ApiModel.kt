package com.lastowski.weatherapp.data

data class CurrentWeatherData(
    val location: LocationData,
    val current: CurrentData
)

data class LocationData(
    val name: String,
    val country: String,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class CurrentData(
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val condition: Condition
)
