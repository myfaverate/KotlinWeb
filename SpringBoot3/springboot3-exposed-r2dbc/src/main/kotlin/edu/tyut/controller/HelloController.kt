package edu.tyut.controller

import edu.tyut.bean.EmailUser
import kotlinx.coroutines.delay
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/hello"])
private final class HelloController(
    private val emailUser: EmailUser
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @GetMapping(value = ["/hello"])
    private final suspend fun hello(): String {
        delay(timeMillis = 100)
        logger.info("HELLO WORLD emailUser: $emailUser")
        return "Hello World!"
    }
}