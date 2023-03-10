package com.gberanger.berealtestapp.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.gberanger.berealtestapp.ui.theme.Typography

private val lightColorScheme = lightColorScheme(
    primary = white,
    secondary = light_grey,
    tertiary = dark_grey,
    background = black
)
@Composable
fun BeRealTestAppTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = Typography,
        content = content
    )
}