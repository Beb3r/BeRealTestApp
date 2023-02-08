package com.gberanger.berealtestapp.login.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gberanger.berealtestapp.common.composables.KeyboardState
import com.gberanger.berealtestapp.common.composables.keyboardAsState
import com.gberanger.berealtestapp.design.theme.components.EditText
import com.gberanger.berealtestapp.design.theme.red
import com.gberanger.berealtestapp.design.theme.white

@Composable
fun LoginUi(
    viewModel: LoginUiViewModel = hiltViewModel(),
    onNavigateToBrowserScreen: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewState) {
        if (viewState is LoginUiViewState.Success) {
            onNavigateToBrowserScreen()
        }
    }

    LoginUi(
        viewState = viewState,
        onSignInButtonClicked = viewModel::onSignInButtonClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginUi(
    viewState: LoginUiViewState,
    onSignInButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var userName by remember { mutableStateOf(TextFieldValue("noel")) }
    var password by remember { mutableStateOf(TextFieldValue("foobar")) }

    val isKeyboardOpen by keyboardAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(isKeyboardOpen == KeyboardState.CLOSED) {
            Image(
                painterResource(com.gberanger.berealtestapp.design.R.drawable.ic_logo),
                contentDescription = stringResource(R.string.login_logo_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(256.dp)
            )
        }

        Text(
            text = stringResource(R.string.login_title),
            color = white,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(128.dp))

        EditText(
            value = userName,
            onValueChange = { value ->
                userName = value
            },
            label = stringResource(R.string.login_hint_username)
        )


        Spacer(modifier = Modifier.height(16.dp))

        EditText(
            value = password,
            onValueChange = { value ->
                password = value
            },
            label = stringResource(R.string.login_hint_password),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (viewState is LoginUiViewState.Error) {
            Text(
                text = stringResource(R.string.login_error_unknown),
                color = red
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (viewState is LoginUiViewState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            } else {
                OutlinedButton(
                    onClick = {
                        onSignInButtonClicked(userName.text, password.text)
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
                        text = stringResource(R.string.login_button),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

        }
    }
}