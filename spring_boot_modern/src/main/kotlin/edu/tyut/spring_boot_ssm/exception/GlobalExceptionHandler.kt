package edu.tyut.spring_boot_ssm.exception

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.HttpMessageWriter
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.DispatcherHandler
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RenderingResponse
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.resource.NoResourceFoundException
import org.springframework.web.reactive.result.view.ViewResolver
import org.springframework.web.server.CoWebExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI
import java.net.URL
import java.net.URLEncoder

@Order(value = -2)
@Component
internal final class GlobalExceptionHandler(
    private val objectMapper: ObjectMapper,
    private val strategies: HandlerStrategies,
    private val dispatcherHandler: DispatcherHandler
) : CoWebExceptionHandler() {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun coHandle(exchange: ServerWebExchange, ex: Throwable) {
        when (ex) {
            is WebExchangeBindException -> {
                this.handlerBindException(exchange = exchange, ex = ex)
            }
            else -> {
                this.handlerOtherException(exchange = exchange, ex = ex)
            }
        }
    }

    private final suspend fun handlerBindException(exchange: ServerWebExchange, ex: WebExchangeBindException) {
        val defaultMessage: String = ex.fieldErrors.firstOrNull()?.defaultMessage.toString()
        logger.error(ex.message, ex)
        exchange.response.apply {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            headers.contentType = MediaType.APPLICATION_JSON
            val result: edu.tyut.spring_boot_ssm.bean.Result<Boolean> = edu.tyut.spring_boot_ssm.bean.Result.failure(message = defaultMessage, data = false)

            // 1
            val resultJson: String = objectMapper.writeValueAsString(result)
            val buffer: DataBuffer = bufferFactory().wrap(resultJson.toByteArray(charset = Charsets.UTF_8))
            writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()

            // 2
            // val buffer: DataBuffer = Jackson2JsonEncoder().encodeValue(
            //     result,
            //     bufferFactory(),
            //     ResolvableType.forInstance(result),
            //     MediaType.APPLICATION_JSON,
            //     null,
            // )
            // writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()
        }.setComplete().awaitSingleOrNull()
    }

    @Suppress("DuplicatedCode")
    private final suspend fun handlerOtherException(exchange: ServerWebExchange, ex: Throwable) {
        val defaultMessage: String = ex.message.toString()
        logger.error(ex.message, ex)
        exchange.response.apply {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            headers.contentType = MediaType.APPLICATION_JSON
            val result: edu.tyut.spring_boot_ssm.bean.Result<Boolean> = edu.tyut.spring_boot_ssm.bean.Result.failure(message = defaultMessage, data = false)
            // 1
            val resultJson: String = objectMapper.writeValueAsString(result)
            val buffer: DataBuffer = bufferFactory().wrap(resultJson.toByteArray(charset = Charsets.UTF_8))
            writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()

            // 2
            // val buffer: DataBuffer = Jackson2JsonEncoder().encodeValue(
            //     result,
            //     bufferFactory(),
            //     ResolvableType.forInstance(result),
            //     MediaType.APPLICATION_JSON,
            //     null,
            // )
            // writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()
        }.setComplete().awaitSingleOrNull()
    }

    private final suspend fun handlerViewException(exchange: ServerWebExchange, ex: Throwable) {
        logger.error(ex.message, ex)
        val response = RenderingResponse.create("index")
            .modelAttribute("message", ex.message)
            .build().awaitSingleOrNull()
        response?.writeTo(exchange, object : ServerResponse.Context{
            override fun messageWriters(): List<HttpMessageWriter<*>?> {
                return strategies.messageWriters()
            }

            override fun viewResolvers(): List<ViewResolver?> {
                return strategies.viewResolvers().apply {
                    logger.info("view resolvers: $this")
                }
            }
        })?.awaitSingleOrNull()
    }

    private final suspend fun handlerHtmlException(exchange: ServerWebExchange, ex: Throwable) {
        logger.error("handlerHtmlException: ${ex.message}", ex)
        if (exchange.request.uri.path.startsWith(prefix = "/html/hello")) {
            exchange.response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            exchange.response.setComplete().awaitSingleOrNull()
            return
        }
        delay(timeMillis = 1000L)

        // 重定向
        // exchange.response.statusCode = HttpStatus.SEE_OTHER
        // exchange.response.headers.location = URI.create("/html/hello?message=${URLEncoder.encode(ex.message, Charsets.UTF_8)}")
        // exchange.response.setComplete().awaitSingleOrNull()

        // 转发
        val path = "/html/hello"
        val request: ServerHttpRequest = exchange.request.mutate()
            .method(HttpMethod.GET) // 可选：你可以保持原始 method
            .uri(URI.create("$path?message=${URLEncoder.encode(ex.message, Charsets.UTF_8)}"))
            .build()
        logger.info("uri: ${request.uri.path}, path: ${request.path}")
        dispatcherHandler.handle(
            exchange.mutate()
                .request(request).build()
        ).awaitSingleOrNull()
    }
}