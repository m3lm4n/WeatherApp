package com.lastowski.weatherapp.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastowski.weatherapp.permssions.GrantState
import com.lastowski.weatherapp.permssions.PermissionProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    private val permissionProvider: PermissionProvider
) : ViewModel() {

    private val _state = MutableStateFlow(ConfigurationViewState())
    val state: StateFlow<ConfigurationViewState> get() = _state

    init {
        viewModelScope.launch {
            combine(
                permissionProvider.permissionsState
            ) { (permissionsState) ->
                ConfigurationViewState(
                    permissionsState.locationGrantState != GrantState.Granted
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    fun onPermissionGrantButtonClick() {
        permissionProvider.requestPermission(PermissionProvider.Permission.CoarseLocation)
    }
}

data class ConfigurationViewState(
    val locationPermissionSectionVisible: Boolean = false
)