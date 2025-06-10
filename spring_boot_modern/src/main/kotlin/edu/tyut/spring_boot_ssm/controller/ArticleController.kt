package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import edu.tyut.spring_boot_ssm.service.ArticleService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@RestController
@RequestMapping(value = ["/article"])
private final class ArticleController private constructor(
    private val articleService: ArticleService
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping
    private final suspend fun add(
        @RequestBody @Valid articleDto: ArticleDto
    ): Result <Boolean> {
        logger.info("adding article: $articleDto")
        val context: CoroutineContext? = coroutineContext
        val id: Int = context?.get(UserContext)?.id ?: 0
        // val isSuccess: Boolean = articleService.add(articleDto = articleDto.copy(createUser = id.toUInt())).await()
        val isSuccess = false
        return if (isSuccess) {
            Result.success(message = "success", data = true)
        } else {
            Result.failure(message = "failure", data = false)
        }
    }
    @GetMapping("/hello1")
    private final suspend fun hello1(): String {
        logger.info("Hello1 -> ${Thread.currentThread()}")
        return "Hello Spring Boot 1世界!"
    }
}