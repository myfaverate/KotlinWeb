package edu.tyut.spring_boot_ssm.exception

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.CoWebExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Order(value = -2)
@Component
internal final class GlobalExceptionHandler : CoWebExceptionHandler() {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun coHandle(exchange: ServerWebExchange, ex: Throwable) {
        logger.error(ex.message, ex)
        exchange.response.apply {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            headers.contentType = MediaType.APPLICATION_JSON
            val result: edu.tyut.spring_boot_ssm.bean.Result<Boolean> = edu.tyut.spring_boot_ssm.bean.Result.failure(message = ex.message.toString(), data = false)
            val resultJson: String = ObjectMapper().writeValueAsString(result)
            val buffer: DataBuffer = bufferFactory().wrap(resultJson.toByteArray())
            writeWith(Mono.just<DataBuffer>(buffer)).awaitSingleOrNull()

            // val buffer: DataBuffer = Jackson2CborEncoder().encodeValue(
            //     result,
            //     bufferFactory(),
            //     ResolvableType.forInstance(result),
            //     MediaType.APPLICATION_JSON,
            //     null,
            // )
        }.setComplete().awaitSingleOrNull()

    }
}