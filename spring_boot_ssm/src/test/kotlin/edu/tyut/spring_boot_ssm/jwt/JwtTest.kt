package edu.tyut.spring_boot_ssm.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import kotlinx.coroutines.test.runTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.security.interfaces.RSAPublicKey
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.test.Test

internal class JwtTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    internal fun getToken(): Unit = runTest {
        val claims: Map<String, Any> = mapOf(
            "id" to 1,
            "username" to "樟树哈皮",
        )
        val token: String = JWT.create()
            .withClaim(
                "user", claims
            )
            .withExpiresAt(Date.from(Instant.now().plus(12, ChronoUnit.HOURS)))
            .sign(Algorithm.HMAC256("edu.tut"))
        // token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
        // .eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Iuaon-agkeWTiOeariJ9LCJleHAiOjE3NDkzMjc5MzZ9
        // .F_h7kNqXro-aLhcio8q0Gvf4Hco7P57G-kF7vwaFIVA
        logger.info("token: $token")
    }

    @Test
    internal fun getTokenInfo(): Unit = runTest {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Iuaon-agkeWTiOeariJ9LCJleHAiOjE3NDkzMjc5MzZ9.F_h7kNqXro-aLhcio8q0Gvf4Hco7P57G-kF7vwaFIVA"
        val jWTVerifier: JWTVerifier = JWT.require(Algorithm.HMAC256("edu.tut"))
            .build()
        val decodedJWT: DecodedJWT = jWTVerifier.verify(token)
        decodedJWT.claims.getOrDefault("user", "").apply {
            logger.info("user: $this")
        }
    }
}