package com.lastowski.weatherapp.data

import com.lastowski.weatherapp.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(@Query("q") query: String, @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY): Response<CurrentWeatherData>

    companion object {
        fun create(): WeatherApiService {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
