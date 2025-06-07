package edu.tyut.spring_boot_ssm.intercepter

import edu.tyut.spring_boot_ssm.jwt.JwtUtil
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.CoWebFilter
import org.springframework.web.server.CoWebFilterChain
import org.springframework.web.server.ServerWebExchange

// CoWebExceptionHandler
@Component
internal final class LoginInterceptor : CoWebFilter() {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)


    override suspend fun filter(
        exchange: ServerWebExchange,
        chain: CoWebFilterChain
    ) {
        val excludePaths: Set<String> = setOf("/user/register", "/user/login", "/user/logout", "/user/hello1")
        if (excludePaths.any { path: String ->
            exchange.request.path.toString().startsWith(prefix = path)
        }){
            chain.filter(exchange = exchange)
            return
        }
        val token: String = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
            ?: ""
        try {
            val claims: Map<String, Any> = JwtUtil.parseToken(token = token)
            logger.info("token: {}, claims: {}", token, claims)
            chain.filter(exchange)
        } catch (e: Exception){
            logger.error(e.message, e)
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            exchange.response.setComplete().awaitSingleOrNull()
        }
    }
}