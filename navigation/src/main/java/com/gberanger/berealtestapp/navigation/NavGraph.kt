package com.gberanger.berealtestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gberanger.berealtestapp.browser.presentation.BrowserUi
import com.gberanger.berealtestapp.login.presentation.LoginUi
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.settings.presentation.SettingsUi

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
            BrowserUi(
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToSettingsScreen = {
                    navController.navigate(Screens.Settings.route)
                }
            )
        }
        composable(Screens.Settings.route) {
            SettingsUi(
                onNavigateToLoginScreen = {
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Browser.route) { inclusive = true }

                    }
                }
            )
        }
    }
}
fun getStartDestination(status: SessionStatusDomainModel) =
    if (status == SessionStatusDomainModel.LOGGED_OUT){
        Screens.Login.route
    } else {
        Screens.Browser.route
    }