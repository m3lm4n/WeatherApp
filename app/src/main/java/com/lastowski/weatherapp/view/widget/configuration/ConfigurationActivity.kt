package com.lastowski.weatherapp.view.widget.configuration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.lastowski.weatherapp.databinding.ActivityConfigurationBinding
import com.lastowski.weatherapp.permssions.PermissionRequestListener
import com.lastowski.weatherapp.view.model.ConfigurationViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigurationActivity : AppCompatActivity() {

    private val permissionRequestListener: PermissionRequestListener by inject()

    private val configurationViewModel: ConfigurationViewModel by viewModel()

    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            configurationViewModel.state.flowWithLifecycle(lifecycle).collect { viewState ->
                binding.viewState = viewState
            }
        }

        binding.grantLocationPermissionButton.setOnClickListener {
            configurationViewModel.onPermissionGrantButtonClick()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionRequestListener.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}