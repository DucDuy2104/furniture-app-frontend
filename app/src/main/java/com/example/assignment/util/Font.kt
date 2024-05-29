package com.example.assignment.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.assignment.R

val gelasioFamily = FontFamily(
    fonts = listOf(
        Font(resId = R.font.gelasio_semibold, weight = FontWeight.SemiBold),
        Font(resId = R.font.gelasio_bold, weight= FontWeight.Bold),
        Font(resId = R.font.gelasio_medium, weight = FontWeight.Medium)
    )
)

val nunitosansFamily = FontFamily(
    fonts = listOf(
        Font(resId = R.font.nunitosans_regular, weight = FontWeight.Normal),
        Font(resId = R.font.nunitosans_semibold, weight = FontWeight.SemiBold),
        Font(resId= R.font.nunitosans_bold, weight = FontWeight.Bold),
        Font(resId = R.font.nunitosans_light, weight = FontWeight.Light)
    )
)

val merriweather = FontFamily(
    fonts = listOf(
        Font(resId = R.font.merriweather_regular, weight = FontWeight.Normal),
        Font(resId = R.font.merriweather_bold, weight = FontWeight.Bold)
    )
)