package com.gberanger.berealtestapp.browser.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gberanger.berealtestapp.design.theme.white

@Composable
fun BrowserUi(
    viewModel: BrowserUiViewModel = hiltViewModel(),
    onNavigateToSettingsScreen: () -> Unit = {}
) {
    Text(text = "Browser", color = white)
}