package com.example.bottonnav.pages

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class SettingsManager(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveSettings(data: SettingsData) {
        prefs.edit().apply {
            putString("name", data.name)
            putString("email", data.email)
            putInt("color", data.appColor.toArgb())
            putBoolean("autoArm", data.autoArm)
            putBoolean("notifications", data.notifications)
            apply()
        }
    }

    fun loadSettings(): SettingsData {
        return SettingsData(
            name = prefs.getString("name", "") ?: "",
            email = prefs.getString("email", "") ?: "",
            appColor = Color(prefs.getInt("color", Color.Yellow.toArgb())),
            autoArm = prefs.getBoolean("autoArm", false),
            notifications = prefs.getBoolean("notifications", false)
        )
    }
}