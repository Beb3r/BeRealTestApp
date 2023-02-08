package com.gberanger.berealtestapp.browser.presentation

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel

sealed class BrowserUiViewState {
    object Loading: BrowserUiViewState()
    object Empty: BrowserUiViewState()
    data class Success(val items: List<BrowserItemDomainModel>): BrowserUiViewState()
    data class Error(val error: Throwable): BrowserUiViewState()
}