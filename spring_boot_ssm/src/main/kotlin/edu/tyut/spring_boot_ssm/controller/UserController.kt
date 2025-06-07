package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.dto.UserLoginDto
import edu.tyut.spring_boot_ssm.entity.UserEntity.username
import edu.tyut.spring_boot_ssm.service.UserService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitFormData
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.awaitFormData

@RestController
@RequestMapping(value = ["/user"])
private final class UserController(
    private val userService: UserService,
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = ["/register"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    private final suspend fun register(
        @ModelAttribute userLoginDto: UserLoginDto
    ): Result<Boolean> {
        // serverWebExchange: ServerWebExchange
        // val username = serverWebExchange.awaitFormData().getFirst("username")
        logger.info("Registering userLoginDto $userLoginDto")
        val isExist: Boolean = userService.isUserExists(username = userLoginDto.username).await()
        logger.info("User already exists $isExist")
        if (isExist) {
            return Result.failure(message = "User already exists", data = true)
        }
        val isSuccess: Boolean = userService.register(username = userLoginDto.username, password = userLoginDto.password).await()
        return if (isSuccess) {
            Result.success(message = "login success", data = true)
        } else {
            Result.failure(message = "login failure", data = false)
        }
    }
    @GetMapping("/hello1")
    private final suspend fun hello1(): String {
        delay(100)
        logger.info("Hello1 -> ${Thread.currentThread()}")
        return "Hello Spring Boot 1世界!"
    }
}