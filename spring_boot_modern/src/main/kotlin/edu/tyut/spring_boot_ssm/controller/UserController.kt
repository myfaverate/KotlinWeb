package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.bean.User
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.dto.NewPasswordDto
import edu.tyut.spring_boot_ssm.dto.UserLoginDto
import edu.tyut.spring_boot_ssm.extension.md5
import edu.tyut.spring_boot_ssm.jwt.JwtUtil
import edu.tyut.spring_boot_ssm.service.UserService
import jakarta.validation.Valid
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.awaitFormData
import java.net.URL
import java.util.Optional
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.hours
import kotlin.time.toJavaDuration

@RestController
@RequestMapping(value = ["/user"])
private final class UserController(
    private val userService: UserService,
    private val redisTemplate: ReactiveStringRedisTemplate
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = ["/register"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    private final suspend fun register(

        @Valid @ModelAttribute userLoginDto: UserLoginDto
    ): Result<Boolean> {
        // serverWebExchange: ServerWebExchange
        // val username = serverWebExchange.awaitFormData().getFirst("username")
        logger.info("Registering userLoginDto $userLoginDto")
        val isExist: Boolean = userService.isUserExists(username = userLoginDto.username!!).await()
        logger.info("User exists? : $isExist")
        if (isExist) {
            return Result.failure(message = "User already exists", data = false)
        }
        val isSuccess: Boolean = userService.register(username = userLoginDto.username, password = userLoginDto.password!!).await()
        return if (isSuccess) {
            Result.success(message = "register success", data = true)
        } else {
            Result.failure(message = "register failure", data = false)
        }
    }
    @PostMapping(value = ["/login"])
    private final suspend fun login(
        @Valid @ModelAttribute userLoginDto: UserLoginDto
    ): Result<String> {
        logger.info("Logging userLoginDto $userLoginDto")
        val user: User = userService.findUserByUsername(username = userLoginDto.username!!).await()
        logger.info("User user : $user")
        if (user.id == 0U){
            return Result.failure(message = "username error", data = "login failure")
        }
        if (user.password == userLoginDto.password!!.md5()) {
            val claims: Map<String, Any> = mapOf(
                "id" to user.id!!.toInt(),
                "username" to user.username,
            )
            val token: String = JwtUtil.generateToken(claims = claims)
            logger.info("Token : $token, claims : $claims")
            val isSuccess: Boolean = redisTemplate.opsForValue().setAndAwait(key = token, value = token, timeout = 12.hours.toJavaDuration())
            logger.info("isSuccess : $isSuccess, thread: ${Thread.currentThread()}")
            return Result.success(message = "login success", data = token)
        }
        return Result.failure(message = "password error", data = "login failure")
    }

    @GetMapping(value = ["/getUserInfo"])
    private final suspend fun getUserInfo(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String
    ): Result<User> {
        val username: String = JwtUtil.parseToken(token = token).getOrDefault(key = "username", defaultValue = "").toString()
        if (username.isEmpty()){
            throw IllegalArgumentException("username is null or blank")
        }
        logger.info("username : $username")
        val user: User = userService.findUserByUsername(username = username).await()
        if (user.id == 0U){
            throw IllegalArgumentException("username is invalid")
        }
        return Result.success(message = "success", data = user)
    }

    @PutMapping(value = ["/updateUser"])
    private final suspend fun updateUser(
        @RequestBody @Validated user: User
    ): Result<Boolean> {
        logger.info("Updating user $user")
        if (user.id == 0U){
            return Result.failure(message = "user update failure", data = false)
        }
        val isSuccess: Boolean = userService.updateUser(user = user).await()
        return if (isSuccess) {
            Result.success(message = "user updated success", data = true)
        } else {
            Result.failure(message = "user update failure", data = false)
        }
    }

    /**
     * kotlin.UInt
     * TODO
     * public static int kotlin.UInt.constructor-impl(int)
     *
     * without it being registered for runtime reflection. Add public static int kotlin.UInt.constructor-impl(int) to the reflection metadata to solve this problem. See https://www.graalvm.org/latest/reference-manual/native-image/metadata/#reflection for help.
     *
     */
    @PostMapping(value = ["/updateAvatar"])
    private final suspend fun updateAvatar(
        serverWebExchange: ServerWebExchange
    ): Result<Boolean> {
        val avatarUrl = serverWebExchange.awaitFormData().getFirst("avatarUrl") ?: ""
        logger.info("Updating avatar $avatarUrl")
        if (!avatarUrl.startsWith(prefix = "https://") && !avatarUrl.startsWith(prefix = "http://")) {
            return Result.failure(message = "avatar url is invalid", data = false)
        }
        val context: CoroutineContext? = coroutineContext
        val id: Int = context?.get(UserContext)?.id ?: 0
        if (id == 0) {
            return Result.failure(message = "avatar update failure", data = false)
        }
        val isSuccess: Boolean = userService.updateAvatar(id = id.toUInt(), avatarUrl = avatarUrl.toString()).await()
        return if (isSuccess) {
            Result.success(message = "avatar update success", data = true)
        } else {
            Result.failure(message = "avatar update failure", data = false)
        }
    }

    @PatchMapping(value = ["/updatePassword"])
    private final suspend fun updatePassword(
        @Valid @RequestBody newPasswordDto: NewPasswordDto,
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
    ): Result<Boolean> {
        val oldPassword: String = newPasswordDto.oldPassword!!
        val newPassword: String = newPasswordDto.newPassword!!
        val confirmPassword: String = newPasswordDto.confirmPassword!!
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            return Result.failure(message = "password is null", data = false)
        }
        // coroutineContext 数据上下文传递
        val context: CoroutineContext? = coroutineContext
        val username: String = context?.get(UserContext)?.username ?: ""
        val id: Int = context?.get(UserContext)?.id ?: 0
        val user: User = userService.findUserByUsername(username = username).await()
        if (user.password != oldPassword.md5()){
            return Result.failure(message = "The old password is incorrect.", data = false)
        }
        if (newPassword != confirmPassword){
            return Result.failure(message = "The passwords do not match.", data = false)
        }
        val isSuccess: Boolean = userService.updatePassword(
            id = id.toUInt(),
            password = newPassword.md5()
        ).await()
        return if (isSuccess) {
            val isSuccess: Boolean = redisTemplate.opsForValue().deleteAndAwait(key = token)
            logger.info("delete token: $token, redisResult, $isSuccess")
            Result.success(message = "success", data = true)
        } else {
            Result.failure(message = "failure", data = false)
        }
    }
    
    @GetMapping(value = ["/hello1"])
    private final suspend fun hello1(
    ): Result<String> {
        // coroutineContext 数据上下文传递
        val context: CoroutineContext? = coroutineContext
        val username: String = context?.get(UserContext)?.username ?: ""
        logger.info("Hello1 -> ${Thread.currentThread()}, name: $username, context: $context")
        return Result.success(message = "success", data = "")
    }
}