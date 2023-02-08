package com.gberanger.berealtestapp.settings.presentation

sealed class SettingsUiViewState {
    object Idle: SettingsUiViewState()
    data class Success(val firstName: String, val lastName: String): SettingsUiViewState()
    object LogoutLoading: SettingsUiViewState()
    object LogoutSuccess: SettingsUiViewState()
}