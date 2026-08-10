package com.bonin.auth.dto

import com.bonin.user.UserResponse

data class AuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer",
    val expiresIn: Long,
    val user: UserResponse
)