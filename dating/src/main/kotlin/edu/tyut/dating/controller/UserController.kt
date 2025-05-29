package edu.tyut.dating.controller

import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "UserController")
@RequestMapping(value = ["/user"])
internal class UserController(
    private val userService: UserService
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/getUsers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    internal fun getUsers(
        @RequestHeader headers: Map<String, String>,
    ): List<User> {
        logger.info("getUsers -> headers: $headers")
        return userService.findAll()
    }

    @GetMapping(value = ["/getUser/{id}"])
    internal fun getUserById(
        @PathVariable(value = "id") id: Int,
        @RequestHeader headers: Map<String, String>,
    ): User {
        logger.info("getUser -> id: $id, headers: $headers")
        return userService.getUserById(id = id)
    }
}