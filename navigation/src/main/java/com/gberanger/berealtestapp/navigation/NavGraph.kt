package com.gberanger.berealtestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gberanger.berealtestapp.browser.presentation.BrowserUi
import com.gberanger.berealtestapp.login.presentation.LoginUi
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sessionStatus: SessionStatusDomainModel
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = getStartDestination(sessionStatus),
        modifier = modifier,
    ) {
        composable(Screens.Login.route) {
            LoginUi(
                onNavigateToBrowserScreen = {
                    navController.navigate(Screens.Browser.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }

                    }
                })
        }
        composable(Screens.Browser.route) {
            BrowserUi()
        }
    }
}

fun getStartDestination(status: SessionStatusDomainModel) =
    if (status == SessionStatusDomainModel.LOGGED_OUT){
        "login"
    } else {
        "browser"
    }