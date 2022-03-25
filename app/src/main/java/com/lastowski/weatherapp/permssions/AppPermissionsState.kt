package com.lastowski.weatherapp.permssions

import com.lastowski.weatherapp.permssions.GrantState.Companion.asGrantState

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