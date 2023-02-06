package com.gberanger.berealtestapp.login.presentation

sealed class LoginUiViewState {
    object Idle: LoginUiViewState()
    object Success: LoginUiViewState()
    object Loading:  LoginUiViewState()
    class Error(error: Throwable): LoginUiViewState()
}