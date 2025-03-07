package com.example.bottonnav

import android.graphics.drawable.Icon
import android.icu.text.AlphabeticIndex.Bucket.LabelType
import androidx.compose.ui.graphics.vector.ImageVector

data class Navaitem(
    val label : String,
    val Icon : ImageVector,
    val  bagdeCount : Int,
)
