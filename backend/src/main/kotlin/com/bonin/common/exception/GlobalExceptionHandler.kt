package com.bonin.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExists(
        exception: EmailAlreadyExistsException
    ): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(
            ApiError(
                status = HttpStatus.CONFLICT.value(),
                code = "EMAIL_ALREADY_EXISTS",
                message = exception.message ?: "Email is already registered"
            )
        )

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentials(
        exception: InvalidCredentialsException
    ): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            ApiError(
                status = HttpStatus.UNAUTHORIZED.value(),
                code = "INVALID_CREDENTIALS",
                message = exception.message ?: "Invalid email or password"
            )
        )

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(
        exception: UserNotFoundException
    ): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ApiError(
                status = HttpStatus.NOT_FOUND.value(),
                code = "USER_NOT_FOUND",
                message = exception.message ?: "User not found"
            )
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiError> {
        val errors = exception.bindingResult
            .fieldErrors
            .associate {
                it.field to (it.defaultMessage ?: "Invalid value")
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                code = "VALIDATION_ERROR",
                message = "Request validation failed",
                errors = errors
            )
        )
    }
}