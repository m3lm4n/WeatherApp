package com.lastowski.weatherapp.permssions

import android.content.pm.PackageManager

enum class GrantState {
    Granted, Denied;

    companion object {
        val Int?.asGrantState: GrantState
            get() = if (this == PackageManager.PERMISSION_GRANTED) Granted else Denied
    }
}