package com.vahanseva.auto_mall.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vahanseva.auto_mall.presentation.screens.*

/**
 * Main navigation setup for VahanSeva app
 */
@Composable
fun VahanSevaNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.CarList.route) {
            CarListScreen(navController = navController)
        }

        composable(
            route = Screen.CarDetail.route,
            arguments = listOf(navArgument("carId") { type = NavType.StringType })
        ) { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            CarDetailScreen(navController = navController, carId = carId)
        }

        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }

        composable(Screen.Messages.route) {
            MessagesScreen(navController = navController)
        }

        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("conversationId") { type = NavType.StringType })
        ) { backStackEntry ->
            val conversationId = backStackEntry.arguments?.getString("conversationId") ?: ""
            ChatScreen(navController = navController, conversationId = conversationId)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screen.AddCar.route) {
            AddCarScreen(navController = navController)
        }
    }
}
