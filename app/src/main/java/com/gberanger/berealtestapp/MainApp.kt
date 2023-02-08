package com.gberanger.berealtestapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.gberanger.berealtestapp.navigation.NavGraph
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

@Composable
fun MainApp(sessionStatus: SessionStatusDomainModel) {
    val navController = rememberNavController()

    NavGraph(
        navController = navController,
        sessionStatus = sessionStatus
    )
}