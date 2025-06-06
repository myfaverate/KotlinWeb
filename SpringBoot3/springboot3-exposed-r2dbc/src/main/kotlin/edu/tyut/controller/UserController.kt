package edu.tyut.controller

import edu.tyut.bean.User
import edu.tyut.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
internal final class UserController internal constructor(
    private val userService: UserService
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @GetMapping(value = ["/findUserById"])
    private final suspend fun findUserById(
        id: Int
    ): User {
        logger.info("${Thread.currentThread()} found user with id: $id")
        return userService.findUserById(id = id).await()
    }
}