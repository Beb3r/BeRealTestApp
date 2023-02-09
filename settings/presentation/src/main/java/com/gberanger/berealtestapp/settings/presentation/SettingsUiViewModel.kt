package com.gberanger.berealtestapp.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.browser.domain.use_cases.BrowserClearDataUseCase
import com.gberanger.berealtestapp.session.domain.use_cases.SessionClearDataUseCase
import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsUiViewModel @Inject constructor(
    private val sessionGetUserDataUseCase: SessionGetUserDataUseCase,
    private val sessionClearDataUseCase: SessionClearDataUseCase,
    private val browserClearDataUseCase: BrowserClearDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SettingsUiViewState>(SettingsUiViewState.Idle)
    val state: StateFlow<SettingsUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SettingsUiViewState.Idle,
    )

    init {
        viewModelScope.launch {
            val data = sessionGetUserDataUseCase()
            _state.value = SettingsUiViewState.Success(data.firstName, data.lastName)
        }
    }

    fun onLogoutButtonClicked() {
        _state.value = SettingsUiViewState.LogoutLoading
        viewModelScope.launch {
            sessionClearDataUseCase()
            browserClearDataUseCase()
            _state.value = SettingsUiViewState.LogoutSuccess
        }
    }
}