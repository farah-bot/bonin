package com.bonin.auth

import com.bonin.auth.dto.AuthResponse
import com.bonin.auth.dto.LoginRequest
import com.bonin.auth.dto.RegisterRequest
import com.bonin.common.exception.EmailAlreadyExistsException
import com.bonin.common.exception.InvalidCredentialsException
import com.bonin.user.User
import com.bonin.user.UserRepository
import com.bonin.user.UserResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    @Transactional
    fun register(request: RegisterRequest): AuthResponse {
        val normalizedEmail = request.email
            .trim()
            .lowercase()

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw EmailAlreadyExistsException()
        }

        val passwordHash = requireNotNull(
            passwordEncoder.encode(request.password)
        ) {
            "Password encoding failed"
        }

        val user = User(
            name = request.name.trim(),
            email = normalizedEmail,
            passwordHash = passwordHash
        )

        val savedUser = try {
            userRepository.saveAndFlush(user)
        } catch (exception: DataIntegrityViolationException) {
            throw EmailAlreadyExistsException()
        }

        return createAuthResponse(savedUser)
    }

    @Transactional(readOnly = true)
    fun login(request: LoginRequest): AuthResponse {
        val normalizedEmail = request.email
            .trim()
            .lowercase()

        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    normalizedEmail,
                    request.password
                )
            )
        } catch (exception: BadCredentialsException) {
            throw InvalidCredentialsException()
        }

        val user = userRepository.findByEmail(normalizedEmail)
            ?: throw InvalidCredentialsException()

        return createAuthResponse(user)
    }

    private fun createAuthResponse(user: User): AuthResponse =
        AuthResponse(
            accessToken = jwtService.generateAccessToken(user),
            expiresIn = jwtService.accessTokenExpiresIn(),
            user = UserResponse.from(user)
        )
}