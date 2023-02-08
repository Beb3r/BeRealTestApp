package com.gberanger.berealtestapp.visualizer.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gberanger.berealtestapp.session.domain.use_cases.SessionGetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VisualizerUiViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sessionGetAccessTokenUseCase: SessionGetAccessTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<VisualizerUiViewState>(VisualizerUiViewState.Loading)
    val state: StateFlow<VisualizerUiViewState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = VisualizerUiViewState.Loading,
    )

    init {
        val url: String = checkNotNull(savedStateHandle["id"])
        viewModelScope.launch {
            val accessToken = sessionGetAccessTokenUseCase.invoke()
            _state.value = VisualizerUiViewState.Success(url, accessToken)
        }
    }
}