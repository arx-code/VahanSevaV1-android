package com.vahanseva.auto_mall.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Placeholder screens for VahanSeva app
 * These will be fully implemented with proper UI and logic
 */

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("VahanSeva Loading...", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Login Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Register Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun CarListScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Car List Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun CarDetailScreen(navController: NavController, carId: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Car Detail Screen", style = MaterialTheme.typography.headlineMedium)
            Text("Car ID: $carId", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Search Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun FavoritesScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Favorites Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun MessagesScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Messages Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ChatScreen(navController: NavController, conversationId: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Chat Screen", style = MaterialTheme.typography.headlineMedium)
            Text("Conversation ID: $conversationId", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun AddCarScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Add Car Screen", style = MaterialTheme.typography.headlineMedium)
    }
}
