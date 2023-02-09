package com.gberanger.berealtestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionGetStatusUseCase: SessionGetStatusUseCase
) : ViewModel() {

    val sessionStatus = flow {
        val status = sessionGetStatusUseCase()
        emit(status)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SessionStatusDomainModel.UNKNOWN,
    )
}