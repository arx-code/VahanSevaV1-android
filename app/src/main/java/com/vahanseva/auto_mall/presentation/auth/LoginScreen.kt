package com.vahanseva.auto_mall.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.presentation.components.buttons.PrimaryButton
import com.vahanseva.auto_mall.presentation.components.buttons.VahanSevaTextButton
import com.vahanseva.auto_mall.presentation.components.inputs.TextInput
import com.vahanseva.auto_mall.presentation.theme.Spacing

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahanseva.auto_mall.presentation.viewmodel.AuthViewModel
import com.vahanseva.auto_mall.presentation.viewmodel.AuthUiState
import androidx.compose.runtime.LaunchedEffect

/**
 * Login screen with authentication
 * - Email/phone input
 * - Password input
 * - Login button
 * - Register link
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    
    val uiState by viewModel.uiState.collectAsState()

    // Handle UI state changes
    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> onLoginSuccess()
            is AuthUiState.Error -> {
                // Handle error (e.g., show snackbar)
            }
            else -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(Spacing.sm))

        Text(
            text = "Login to continue",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        // Email input
        TextInput(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = "Email or Phone",
            placeholder = "Enter your email or phone",
            error = emailError,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        // Password input
        TextInput(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },
            label = "Password",
            placeholder = "Enter your password",
            error = passwordError,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.lg))

        // Login button
        PrimaryButton(
            text = "Login",
            onClick = {
                // Simple validation
                var hasError = false
                if (email.isEmpty()) {
                    emailError = "Email is required"
                    hasError = true
                }
                if (password.isEmpty()) {
                    passwordError = "Password is required"
                    hasError = true
                }

                if (!hasError) {
                    viewModel.login(email, password)
                }
            },
            loading = uiState is AuthUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        // Register link
        VahanSevaTextButton(
            text = "Don't have an account? Register",
            onClick = onNavigateToRegister
        )
    }
}
