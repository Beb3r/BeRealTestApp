package com.gberanger.berealtestapp.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginUiViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(LoginUiViewState.Idle)
    val state: StateFlow<LoginUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LoginUiViewState.Idle,
    )
}