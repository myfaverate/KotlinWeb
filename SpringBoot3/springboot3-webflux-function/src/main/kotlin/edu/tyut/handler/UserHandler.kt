package edu.tyut.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.pathVariableOrNull

@Component
internal final class UserHandler {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    internal final suspend fun a(serverRequest: ServerRequest): ServerResponse {
        val id: String? = serverRequest.pathVariableOrNull("id")
        logger.info("User: $id, thread: ${Thread.currentThread()}")
        return ok().bodyValueAndAwait(body = "user: $id")
    }
}