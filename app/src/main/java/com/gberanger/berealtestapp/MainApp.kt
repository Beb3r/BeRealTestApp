package com.gberanger.berealtestapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.gberanger.berealtestapp.navigation.NavGraph
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainApp(sessionStatus: SessionStatusDomainModel) {
    val navController = rememberAnimatedNavController()

    NavGraph(
        navController = navController,
        sessionStatus = sessionStatus
    )
}