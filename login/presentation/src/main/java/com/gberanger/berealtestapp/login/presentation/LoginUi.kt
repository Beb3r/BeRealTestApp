package com.gberanger.berealtestapp.login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun LoginUi(
    viewModel: LoginUiViewModel,
    //openHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginUi(
    viewState: LoginUiViewState,
    openHome: () -> Unit,
    modifier: Modifier = Modifier,
) {

}