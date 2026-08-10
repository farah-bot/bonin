package com.bonin.auth

import com.bonin.security.JwtProperties
import com.bonin.user.User
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtService(
    private val jwtEncoder: JwtEncoder,
    private val properties: JwtProperties
) {

    fun generateAccessToken(user: User): String {
        val now = Instant.now()

        val claims = JwtClaimsSet.builder()
            .issuer(properties.issuer)
            .issuedAt(now)
            .expiresAt(now.plus(properties.accessTokenTtl))
            .subject(user.id.toString())
            .claim("email", user.email)
            .build()

        return jwtEncoder
            .encode(JwtEncoderParameters.from(claims))
            .tokenValue
    }

    fun accessTokenExpiresIn(): Long =
        properties.accessTokenTtl.toSeconds()
}