package com.gberanger.berealtestapp.browser.presentation

import com.gberanger.berealtestapp.browser.domain.models.BrowserItemDomainModel

sealed class BrowserUiViewState {
    object Loading : BrowserUiViewState()

    sealed class Success(open val folderName: String) : BrowserUiViewState() {
        data class Empty(override val folderName: String) : Success(folderName)
        data class Items(override val folderName: String, val items: List<BrowserItemDomainModel>) :
            Success(folderName)
    }

    data class Error(val error: Throwable) : BrowserUiViewState()
}