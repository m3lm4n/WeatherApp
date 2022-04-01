package com.lastowski.weatherapp.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.lastowski.weatherapp.permissions.PermissionProvider
import kotlinx.coroutines.tasks.await

class LocationProvider(
    private val appContext: Context,
    private val permissionProvider: PermissionProvider
) {

    @SuppressLint("MissingPermission")
    suspend fun getLocation(): Location? {
        val locationProviderClient =
            LocationServices.getFusedLocationProviderClient(appContext)

        if (!permissionProvider.hasPermission(PermissionProvider.Permission.CoarseLocation)) {
            return null
        }

        val location = locationProviderClient.lastLocation.await()

        return Location(location.latitude, location.longitude)
    }

    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}

