package com.gberanger.berealtestapp.navigation
sealed class Screens(val route: String) {
    companion object {
        private const val ROUTE_LOGIN = "login"
        private const val ROUTE_BROWSER = "browser"
        private const val ROUTE_SETTINGS = "settings"
        private const val ROUTE_VISUALIZER = "visualizer/{id}"
    }
    object Login : Screens(ROUTE_LOGIN)
    object Browser : Screens(ROUTE_BROWSER)
    object Settings : Screens(ROUTE_SETTINGS)

    object Visualizer: Screens(ROUTE_VISUALIZER) {
        fun createRoute(id: String): String {
            return "visualizer/$id"
        }
    }
}