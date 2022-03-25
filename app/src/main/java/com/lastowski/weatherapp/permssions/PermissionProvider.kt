package com.lastowski.weatherapp.permssions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import com.lastowski.weatherapp.common.ActivityLifecycleAdapter
import com.lastowski.weatherapp.permssions.AppPermissionsState.Companion.asPermissionsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PermissionProvider(val appContext: Context) : ActivityLifecycleAdapter(),
    PermissionRequestListener {

    private val PERMISSION_REQUEST_CODE = 1

    private val _permissionsState = MutableStateFlow(AppPermissionsState())
    val permissionsState: StateFlow<AppPermissionsState> = _permissionsState

    private var currentActivity: Activity? = null

    init {
        _permissionsState.value = AppPermissionsState(
            hasPermission(Permission.CoarseLocation)
        )
    }

    private fun hasPermission(permission: Permission): GrantState =
        when (appContext.checkSelfPermission(permission.name)) {
            PackageManager.PERMISSION_GRANTED -> GrantState.Granted
            else -> GrantState.Denied
        }

    fun requestPermission(permission: Permission) {
        val activity = currentActivity ?: return
        activity.requestPermissions(arrayOf(permission.name), PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            _permissionsState.value =
                permissions.zip(grantResults.toList()).toMap().asPermissionsState
        }
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityStopped(activity: Activity) {
        if (currentActivity == activity) {
            currentActivity = null
        }
    }

    sealed class Permission(val name: String) {
        object CoarseLocation : Permission(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}

