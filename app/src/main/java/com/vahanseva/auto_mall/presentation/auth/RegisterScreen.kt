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
import com.vahanseva.auto_mall.presentation.components.buttons.PrimaryButton
import com.vahanseva.auto_mall.presentation.components.buttons.VahanSevaTextButton
import com.vahanseva.auto_mall.presentation.components.inputs.TextInput
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Register screen with mock registration
 * - Name input
 * - Email input
 * - Phone input
 * - Password input
 * - Register button
 * - Login link
 */
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(Spacing.sm))

        Text(
            text = "Register to get started",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        // Name input
        TextInput(
            value = name,
            onValueChange = {
                name = it
                nameError = null
            },
            label = "Full Name",
            placeholder = "Enter your name",
            error = nameError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        // Email input
        TextInput(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = "Email",
            placeholder = "Enter your email",
            error = emailError,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        // Phone input
        TextInput(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = null
            },
            label = "Phone",
            placeholder = "Enter your phone number",
            error = phoneError,
            keyboardType = KeyboardType.Phone,
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
            placeholder = "Create a password",
            error = passwordError,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.lg))

        // Register button
        PrimaryButton(
            text = "Register",
            onClick = {
                // Simple validation
                var hasError = false
                if (name.isEmpty()) {
                    nameError = "Name is required"
                    hasError = true
                }
                if (email.isEmpty()) {
                    emailError = "Email is required"
                    hasError = true
                }
                if (phone.isEmpty()) {
                    phoneError = "Phone is required"
                    hasError = true
                }
                if (password.isEmpty()) {
                    passwordError = "Password is required"
                    hasError = true
                }

                if (!hasError) {
                    // Mock registration - always succeeds
                    isLoading = true
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        isLoading = false
                        onRegisterSuccess()
                    }, 1000)
                }
            },
            loading = isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        // Login link
        VahanSevaTextButton(
            text = "Already have an account? Login",
            onClick = onNavigateToLogin
        )
    }
}
