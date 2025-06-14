package edu.tyut.spring_boot_ssm.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
private final class HelloController internal constructor(
    private val environment: Environment
){
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @GetMapping("/hello")
    private final suspend fun hello(): String {
        logger.info("Hello -> ${Thread.currentThread()}")
        return "Hello Spring Boot 世界!"
    }
    @GetMapping("/hello1")
    private final suspend fun hello1(): String {
        logger.info("Hello1 -> ${Thread.currentThread()}")
        return "Hello Spring Boot 1世界!, 当前环境: ${environment.activeProfiles.joinToString()}"
    }
}