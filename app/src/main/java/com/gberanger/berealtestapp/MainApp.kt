package com.gberanger.berealtestapp

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gberanger.berealtestapp.navigation.NavGraph
import com.gberanger.berealtestapp.session.domain.models.SessionStatusDomainModel

@Composable
fun MainApp(sessionStatus: SessionStatusDomainModel) {
    val navController = rememberNavController()

    NavGraph(
        navController = navController,
        modifier = Modifier.safeContentPadding(),
        sessionStatus = sessionStatus
    )
}