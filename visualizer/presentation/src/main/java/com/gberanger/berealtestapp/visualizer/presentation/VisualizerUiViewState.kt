package com.gberanger.berealtestapp.visualizer.presentation

sealed class VisualizerUiViewState {
    object Loading: VisualizerUiViewState()
    data class Success(val url: String, val accessToken: String): VisualizerUiViewState()
}