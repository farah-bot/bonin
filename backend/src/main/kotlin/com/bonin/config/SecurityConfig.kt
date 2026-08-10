package com.bonin.config

import com.bonin.security.CustomUserDetailsService
import com.bonin.security.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import java.util.Base64
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun jwtSecretKey(
        properties: JwtProperties
    ): SecretKey {
        val decodedSecret =
            Base64.getDecoder().decode(properties.secret)

        require(decodedSecret.size >= 32) {
            "JWT secret must be at least 256 bits"
        }

        return SecretKeySpec(
            decodedSecret,
            "HmacSHA256"
        )
    }

    @Bean
    fun jwtEncoder(
        secretKey: SecretKey
    ): JwtEncoder =
        NimbusJwtEncoder
            .withSecretKey(secretKey)
            .algorithm(MacAlgorithm.HS256)
            .build()

    @Bean
    fun jwtDecoder(
        secretKey: SecretKey
    ): JwtDecoder =
        NimbusJwtDecoder
            .withSecretKey(secretKey)
            .macAlgorithm(MacAlgorithm.HS256)
            .build()

    @Bean
    fun authenticationProvider(
        userDetailsService: CustomUserDetailsService,
        passwordEncoder: PasswordEncoder
    ): DaoAuthenticationProvider =
        DaoAuthenticationProvider(
            userDetailsService
        ).apply {
            setPasswordEncoder(passwordEncoder)
        }

    @Bean
    fun authenticationManager(
        authenticationProvider: DaoAuthenticationProvider
    ): AuthenticationManager =
        ProviderManager(authenticationProvider)

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {

        http
            .csrf {
                it.disable()
            }
            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/api/v1/auth/**",
                        "/actuator/health",
                        "/actuator/health/**"
                    )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }

        return http.build()
    }
}