package com.example.bottonnav.pages

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val manager = SettingsManager(application.applicationContext)

    var settings by mutableStateOf(manager.loadSettings())
        private set

    fun updateSettings(updated: SettingsData) {
        settings = updated
        manager.saveSettings(updated)
    }
}
