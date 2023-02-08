package com.gberanger.berealtestapp.settings.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gberanger.berealtestapp.design.theme.white

@Composable
fun SettingsUi(
    viewModel: SettingsUiViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit = {}
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewState) {
        if (viewState is SettingsUiViewState.LogoutSuccess) {
            onNavigateToLoginScreen()
        }
    }

    SettingsUi(
        viewState = viewState,
        viewModel::onLogoutButtonClicked
    )
}

@Composable
fun SettingsUi(
    viewState: SettingsUiViewState,
    onLogoutButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(R.drawable.ic_settings),
            contentDescription = stringResource(R.string.settings_icon_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(192.dp)
        )
        if (viewState is SettingsUiViewState.Success) {
            Text(
                text = "${viewState.firstName}  ${viewState.lastName}",
                color = white
            )
        }

        Spacer(modifier = Modifier.height(128.dp))

        Text(
            text = stringResource(R.string.settings_app_name),
            color = white
        )

        Text(
            text = "v${BuildConfig.VERSION_NAME} #${BuildConfig.VERSION_CODE}",
            color = white
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (viewState is SettingsUiViewState.LogoutLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            } else {
                OutlinedButton(
                    onClick = {
                        onLogoutButtonClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    border = BorderStroke(
                        width = 1.5.dp,
                        color = white,
                    )
                ) {
                    Text(
                        text = stringResource(R.string.settings_log_out),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}