package com.gberanger.berealtestapp.login.presentation

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gberanger.berealtestapp.design.theme.components.EditText
import com.gberanger.berealtestapp.design.theme.white

@Composable
fun LoginUi(
    viewModel: LoginUiViewModel = hiltViewModel(),
    //openHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    LoginUi(viewState = viewState)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginUi(
    viewState: LoginUiViewState,
    //openHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var userName by remember { mutableStateOf(TextFieldValue("noel")) }
    var password by remember { mutableStateOf(TextFieldValue("foobar")) }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(com.gberanger.berealtestapp.design.R.drawable.ic_logo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(256.dp)
        )

        Text(
            text = "BeReal Test App",
            color = white,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(128.dp))

        EditText(
            value = userName,
            onValueChange = { value ->
                userName = value
            },
            label = "Username"
        )


        Spacer(modifier = Modifier.height(16.dp))

        EditText(
            value = password,
            onValueChange = { value ->
                password = value
            },
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Box(
            modifier = Modifier.fillMaxSize().weight(1f)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                border = BorderStroke(
                    width = 1.5.dp,
                    color = white,
                )
            ) {
                Text(text = "Sign In")
            }
        }
    }
}