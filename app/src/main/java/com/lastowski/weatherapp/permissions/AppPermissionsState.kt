package com.lastowski.weatherapp.permissions

import com.lastowski.weatherapp.permissions.GrantState.Companion.asGrantState

data class AppPermissionsState(
    val locationGrantState: GrantState = GrantState.Denied
) {
    companion object {
        val Map<String, Int>.asPermissionsState
            get() = AppPermissionsState(
                locationGrantState = this[PermissionProvider.Permission.CoarseLocation.name].asGrantState
            )
    }
}