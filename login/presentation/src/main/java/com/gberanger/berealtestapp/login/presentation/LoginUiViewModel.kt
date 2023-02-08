package com.gberanger.berealtestapp.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.common.Result
import com.gberanger.berealtestapp.common.data
import com.gberanger.berealtestapp.common.extensions.toBase64
import com.gberanger.berealtestapp.common.successOr
import com.gberanger.berealtestapp.login.domain.models.LoginUserCredentialsDomainModel
import com.gberanger.berealtestapp.login.domain.models.LoginUserDataDomainModel
import com.gberanger.berealtestapp.login.domain.use_cases.LoginGetUserUseCase
import com.gberanger.berealtestapp.session.domain.models.SessionDataDomainModel
import com.gberanger.berealtestapp.session.domain.use_cases.SessionSetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginUiViewModel @Inject constructor(
    private val getUserUseCase: LoginGetUserUseCase,
    private val sessionSetDataUseCase: SessionSetDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LoginUiViewState>(LoginUiViewState.Idle)
    val state: StateFlow<LoginUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LoginUiViewState.Idle,
    )

    fun onSignInButtonClicked(username: String, password: String) {
        _state.value = LoginUiViewState.Loading
        val credentials = LoginUserCredentialsDomainModel(
            accessToken = "$username:$password".toBase64()
        )

        viewModelScope.launch {
            val result = getUserUseCase.invoke(credentials)
            if (result is Result.Error) {
                _state.value = LoginUiViewState.Error(Throwable())
            } else {
                saveSessionData(result, credentials)
                _state.value = LoginUiViewState.Success
            }
        }
    }

    private suspend fun saveSessionData(
        result: Result<LoginUserDataDomainModel>,
        credentials: LoginUserCredentialsDomainModel
    ) {
        with(result.successOr(LoginUserDataDomainModel.DEFAULT)) {
            val sessionData = SessionDataDomainModel(
                firstName = firstName,
                lastName = lastName,
                accessToken = credentials.accessToken,
                rootItemId = rootItemId,
                rootItemName = rootItemName

            )
            sessionSetDataUseCase.invoke(sessionData)
        }
    }
}