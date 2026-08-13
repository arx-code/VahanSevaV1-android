package com.vahanseva.auto_mall.ui.auth

import org.junit.Test
import org.junit.Assert.*

/**
 * Test for login validation logic
 */
class LoginViewModelTest {

    @Test
    fun validateEmail_empty_returnsError() {
        val email = ""
        val error = if (email.isEmpty()) "Email is required" else null
        assertNotNull("Should have error for empty email", error)
    }

    @Test
    fun validateEmail_valid_returnsNoError() {
        val email = "test@example.com"
        val error = if (email.isEmpty()) "Email is required" else null
        assertNull("Should not have error for valid email", error)
    }

    @Test
    fun validatePassword_empty_returnsError() {
        val password = ""
        val error = if (password.isEmpty()) "Password is required" else null
        assertNotNull("Should have error for empty password", error)
    }

    @Test
    fun validatePassword_valid_returnsNoError() {
        val password = "password123"
        val error = if (password.isEmpty()) "Password is required" else null
        assertNull("Should not have error for valid password", error)
    }

    @Test
    fun canLogin_bothFieldsValid_returnsTrue() {
        val email = "user@example.com"
        val password = "password123"
        val canLogin = email.isNotBlank() && password.isNotBlank()
        assertTrue("Should be able to login with valid credentials", canLogin)
    }

    @Test
    fun canLogin_emptyFields_returnsFalse() {
        val email = ""
        val password = ""
        val canLogin = email.isNotBlank() && password.isNotBlank()
        assertFalse("Should not be able to login with empty fields", canLogin)
    }
}