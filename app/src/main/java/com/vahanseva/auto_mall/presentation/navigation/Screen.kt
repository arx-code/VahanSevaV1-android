package com.vahanseva.auto_mall.presentation.navigation

/**
 * Navigation routes for VahanSeva app
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object CarList : Screen("car_list")
    object CarDetail : Screen("car_detail/{carId}") {
        fun createRoute(carId: String) = "car_detail/$carId"
    }
    object Search : Screen("search")
    object Favorites : Screen("favorites")
    object Messages : Screen("messages")
    object Chat : Screen("chat/{conversationId}") {
        fun createRoute(conversationId: String) = "chat/$conversationId"
    }
    object Profile : Screen("profile")
    object AddCar : Screen("add_car")
}
