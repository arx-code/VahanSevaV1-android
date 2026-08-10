package com.vahanseva.auto_mall.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vahanseva.auto_mall.presentation.navigation.Screen

/**
 * Home Screen - Main dashboard for VahanSeva
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VahanSeva Auto Mall") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                        navController.navigate(Screen.Home.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") },
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                        navController.navigate(Screen.Search.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedTab == 2,
                    onClick = {
                        selectedTab = 2
                        navController.navigate(Screen.Favorites.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Messages") },
                    label = { Text("Messages") },
                    selected = selectedTab == 3,
                    onClick = {
                        selectedTab = 3
                        navController.navigate(Screen.Messages.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 4,
                    onClick = {
                        selectedTab = 4
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddCar.route) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Car")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to VahanSeva",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your trusted platform for buying and selling used cars",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(Screen.CarList.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Browse Cars")
            }
        }
    }
}
