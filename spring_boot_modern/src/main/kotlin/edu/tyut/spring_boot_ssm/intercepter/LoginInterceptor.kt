package edu.tyut.spring_boot_ssm.intercepter

import com.fasterxml.jackson.databind.ObjectMapper
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.jwt.JwtUtil
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.CoWebFilter
import org.springframework.web.server.CoWebFilterChain
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.util.pattern.PathPattern
import org.springframework.web.util.pattern.PathPatternParser

// CoWebExceptionHandler
@Component
internal final class LoginInterceptor internal constructor(
    private val redisTemplate: ReactiveStringRedisTemplate
) : CoWebFilter() {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun filter(
        exchange: ServerWebExchange,
        chain: CoWebFilterChain
    ) {
        val excludePaths: List<PathPattern> = listOf(
            PathPatternParser.defaultInstance.parse("/user/register"),
            PathPatternParser.defaultInstance.parse("/user/login"),
            PathPatternParser.defaultInstance.parse("/html/*"),
            PathPatternParser.defaultInstance.parse("/favicon.ico"),
            PathPatternParser.defaultInstance.parse("/.well-known/**"),
        )
        if (excludePaths.any { pathPattern: PathPattern ->
                pathPattern.matches(exchange.request.path.pathWithinApplication())
        }){
            chain.filter(exchange = exchange)
            return
        }

        logger.info("path: ${exchange.request.path}")

        val token: String = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
            ?: ""

        if (token.isBlank()){
            throw IllegalArgumentException("token is null or empty")
            // val defaultMessage = "token is null or empty"
            // exchange.response.apply {
            //     statusCode = HttpStatus.UNAUTHORIZED
            //     headers.contentType = MediaType.APPLICATION_JSON
            //     val result: edu.tyut.spring_boot_ssm.bean.Result<Boolean> = edu.tyut.spring_boot_ssm.bean.Result.failure(message = defaultMessage, data = false)
            //     val resultJson: String = objectMapper.writeValueAsString(result)
            //     val buffer: DataBuffer = bufferFactory().wrap(resultJson.toByteArray(charset = Charsets.UTF_8))
            //     writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()
            // }.setComplete().awaitSingleOrNull()
            // return
        }

        val redisToken: String? = redisTemplate.opsForValue().getAndAwait(key = token)
        if (redisToken.isNullOrEmpty()){
            throw IllegalArgumentException("token is invalid")
        }

        val claims: Map<String, Any> = JwtUtil.parseToken(token = token)
        logger.info("token: {}, claims: {}", token, claims)
        val id: Int = claims.getOrDefault(key = "id", defaultValue = 0).toString().toIntOrNull() ?: 0
        val username: String = claims.getOrDefault(key = "username", defaultValue = "").toString()
        withContext(context = UserContext(id = id, username = username)) {
            chain.filter(exchange)
        }

        // try {
        //     val claims: Map<String, Any> = JwtUtil.parseToken(token = token)
        //     logger.info("token: {}, claims: {}", token, claims)
        //     val id: Int = claims.getOrDefault(key = "id", defaultValue = 0).toString().toIntOrNull() ?: 0
        //     val username: String = claims.getOrDefault(key = "username", defaultValue = "").toString()
        //     withContext(context = UserContext(id = id, username = username)) {
        //         chain.filter(exchange)
        //     }
        // } catch (e: Exception){
        //     logger.error(e.message, e)
        //     val defaultMessage = "token is invalid"
        //     exchange.response.apply {
        //         statusCode = HttpStatus.UNAUTHORIZED
        //         headers.contentType = MediaType.APPLICATION_JSON
        //         val result: edu.tyut.spring_boot_ssm.bean.Result<Boolean> = edu.tyut.spring_boot_ssm.bean.Result.failure(message = defaultMessage, data = false)
        //         val resultJson: String = objectMapper.writeValueAsString(result)
        //         val buffer: DataBuffer = bufferFactory().wrap(resultJson.toByteArray(charset = Charsets.UTF_8))
        //         writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()
        //     }.setComplete().awaitSingleOrNull()
        // }
    }
}