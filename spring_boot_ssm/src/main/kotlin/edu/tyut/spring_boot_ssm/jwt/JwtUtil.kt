package edu.tyut.spring_boot_ssm.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

private const val SECRET_KEY: String = "edu.tyut"
private const val CLAIMS_KEY: String = "claims"

internal object JwtUtil {
    internal fun generateToken(claims: Map<String, Any>): String {
        return JWT.create()
            .withClaim(CLAIMS_KEY, claims)
            .withExpiresAt(Date.from(Instant.now().plus(12, ChronoUnit.HOURS)))
            .sign(Algorithm.HMAC256(SECRET_KEY))
    }
    internal fun parseToken(token: String): Map<String, Any> {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
            .build()
            .verify(token)
            .claims
    }
}