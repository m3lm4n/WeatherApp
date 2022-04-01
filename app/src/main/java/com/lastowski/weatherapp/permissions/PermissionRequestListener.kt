package com.lastowski.weatherapp.permissions

interface PermissionRequestListener {
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
}