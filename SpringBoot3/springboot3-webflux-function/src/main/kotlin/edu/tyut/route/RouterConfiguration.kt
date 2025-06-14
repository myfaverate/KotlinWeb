package edu.tyut.route

import edu.tyut.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
internal class RouterConfiguration {
    @Bean
    internal fun userRouter(
        userHandler: UserHandler
    ): RouterFunction<ServerResponse> = coRouter {
        "/user".nest {
            GET(pattern = "/user/{id}", predicate = accept(MediaType.ALL), f = userHandler::a)
        }
    }.filter { request: ServerRequest, handler: HandlerFunction<ServerResponse> ->
        handler.handle(request)
    }
}