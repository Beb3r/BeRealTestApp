package com.gberanger.berealtestapp.visualizer.presentation

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun VisualizerUi(
    viewModel: VisualizerUiViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    VisualizerUi(viewState = viewState)
}

@Composable
fun VisualizerUi(
    viewState: VisualizerUiViewState
) {
    var scale by remember { mutableStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (viewState is VisualizerUiViewState.Success) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${BuildConfig.BASE_URL}/items/${viewState.url}/data")
                    .addHeader("Authorization", "Basic ${viewState.accessToken}")
                    .crossfade(true)
                    .build(),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            scale = when {
                                scale < 0.5f -> 0.5f
                                scale > 3f -> 3f
                                else -> scale * zoom
                            }
                        }
                    }
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}