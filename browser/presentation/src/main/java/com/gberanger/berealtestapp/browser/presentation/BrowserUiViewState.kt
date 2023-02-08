package com.gberanger.berealtestapp.browser.presentation

sealed class BrowserUiViewState {
    object Loading: BrowserUiViewState()
    object Success: BrowserUiViewState()
    object Error: BrowserUiViewState()
}