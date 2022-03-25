package com.lastowski.weatherapp.permssions

interface PermissionRequestListener {
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
}