package com.bonin.common.exception

import java.time.Instant

data class ApiError(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val code: String,
    val message: String,
    val errors: Map<String, String>? = null
)