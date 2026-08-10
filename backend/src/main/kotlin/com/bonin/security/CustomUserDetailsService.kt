package com.bonin.security

import com.bonin.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val email = username.trim().lowercase()

        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found")

        return org.springframework.security.core.userdetails.User
            .withUsername(user.email)
            .password(user.passwordHash)
            .roles("USER")
            .build()
    }
}