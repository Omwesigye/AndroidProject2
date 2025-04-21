package com.example.bottonnav.pages

import androidx.compose.ui.graphics.Color

data class SettingsData(
    val name: String = "",
    val email: String = "",
    val appColor: Color = Color.Yellow,
    val autoArm: Boolean = false,
    val notifications: Boolean = false
)
