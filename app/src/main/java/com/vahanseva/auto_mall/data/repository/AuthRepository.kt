package com.vahanseva.auto_mall.data.repository

import com.vahanseva.auto_mall.data.local.UserDao
import com.vahanseva.auto_mall.data.model.AuthResponse
import com.vahanseva.auto_mall.data.model.LoginRequest
import com.vahanseva.auto_mall.data.model.RegisterRequest
import com.vahanseva.auto_mall.data.model.User
import com.vahanseva.auto_mall.data.remote.AuthService
import com.vahanseva.auto_mall.data.remote.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository for authentication and user management
 */
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val userService: UserService,
    private val userDao: UserDao
) {
    /**
     * Login user with email and password
     */
    fun login(email: String, password: String): Flow<Result<AuthResponse>> = flow {
        try {
            emit(Result.Loading)
            val response = authService.login(LoginRequest(email, password))
            userDao.insertUser(response.user)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Register a new user
     */
    fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        location: String
    ): Flow<Result<AuthResponse>> = flow {
        try {
            emit(Result.Loading)
            val request = RegisterRequest(email, password, name, phone, location)
            val response = authService.register(request)
            userDao.insertUser(response.user)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Logout user
     */
    fun logout(token: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            authService.logout("Bearer $token")
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Get current user profile
     */
    fun getProfile(token: String): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val user = authService.getProfile("Bearer $token")
            userDao.insertUser(user)
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Get user by ID
     */
    fun getUserById(userId: String, token: String): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val user = userService.getUserById(userId, "Bearer $token")
            userDao.insertUser(user)
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Update user profile
     */
    fun updateProfile(user: User, token: String): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val updatedUser = userService.updateProfile(user, "Bearer $token")
            userDao.insertUser(updatedUser)
            emit(Result.Success(updatedUser))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Get cached user from local database
     */
    fun getCachedUser(userId: String): Flow<User?> = userDao.getUserById(userId)

    /**
     * Refresh auth token
     */
    fun refreshToken(refreshToken: String): Flow<Result<AuthResponse>> = flow {
        try {
            emit(Result.Loading)
            val response = authService.refreshToken(refreshToken)
            userDao.insertUser(response.user)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
