package com.gberanger.berealtestapp.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gberanger.berealtestapp.browser.presentation.BrowserUi
import com.gberanger.berealtestapp.login.presentation.LoginUi
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.gberanger.berealtestapp.settings.presentation.SettingsUi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

private const val TRANSITION_DURATION = 400

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sessionStatus: SessionStatusDomainModel
) {
    AnimatedNavHost(
        navController = navController,
        route = "root_host",
        startDestination = getStartDestination(sessionStatus),
        modifier = modifier,
    ) {
        composable(Screens.Login.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screens.Settings.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screens.Browser.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            }) {
            LoginUi(
                onNavigateToBrowserScreen = {
                    navController.navigate(Screens.Browser.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }

                    }
                })
        }
        composable(
            Screens.Browser.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screens.Login.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screens.Settings.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Screens.Settings.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            },
        ) {
            BrowserUi(
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToSettingsScreen = {
                    navController.navigate(Screens.Settings.route)
                }
            )
        }
        composable(Screens.Settings.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screens.Browser.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screens.Browser.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    Screens.Login.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(TRANSITION_DURATION)
                        )
                    else -> null
                }
            }) {
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
    if (status == SessionStatusDomainModel.LOGGED_OUT) {
        Screens.Login.route
    } else {
        Screens.Browser.route
    }