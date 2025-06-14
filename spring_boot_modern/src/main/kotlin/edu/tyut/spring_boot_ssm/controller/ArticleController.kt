package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import edu.tyut.spring_boot_ssm.service.ArticleService
import jakarta.validation.Valid
import kotlinx.coroutines.delay
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
    ): Result<Boolean> {
        logger.info("adding article: $articleDto")
        val context: CoroutineContext? = coroutineContext
        val id: Int = context?.get(UserContext)?.id ?: 0
        val isSuccess: Boolean = articleService.add(articleDto = articleDto.copy(createUser = id.toUInt())).await()
        return if (isSuccess) {
            Result.success(message = "success", data = true)
        } else {
            Result.failure(message = "failure", data = false)
        }
    }

    /**
     *[2025-06-14 03:22:15.862] 21028 [spring_boot_ssm]  [lettuce-nioEventLoop-4-1] Loggers.java:304 WARN  reactor.core.Exceptions : throwIfFatal detected a jvm fatal exception, which is thrown and logged below:
     * java.lang.NoClassDefFoundError: Could not initialize class edu.tyut.spring_boot_ssm.entity.ArticleEntity
     *         at edu.tyut.spring_boot_ssm.dao.impl.ArticleDaoImpl.list$suspendImpl(ArticleDaoImpl.kt:58)
     *         Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
     * Error has been observed at the following site(s):
     *         *__checkpoint ⇢ Handler edu.tyut.spring_boot_ssm.controller.ArticleController#list(int, int, Integer, String, Continuation) [DispatcherHandler]
     * Original Stack Trace:
     *
     */
    @GetMapping
    private final suspend fun list(
        @RequestParam(value = "pageIndex") pageIndex: Int,
        @RequestParam(value = "pageSize") pageSize: Int,
        @RequestParam(value = "categoryId", required = false) categoryId: Int?,
        @RequestParam(value = "state", required = false) state: String?,
    ): Result<List<Article>> {
        logger.info("list pageIndex: $pageIndex, pageSize: $pageSize, categoryId: $categoryId, state: $state")
        val articles: List<Article> =
            articleService.list(pageIndex = pageIndex, pageSize = pageSize, categoryId = categoryId, state = state)
                .await()
        return Result.success(message = "success", data = articles)
    }

    @GetMapping("/hello1")
    private final suspend fun hello1(): String {
        delay(100)
        logger.info("Hello1 -> ${Thread.currentThread()}")
        return "Hello Spring Boot!"
    }
}