package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import edu.tyut.spring_boot_ssm.service.CategoryService
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@RestController
@RequestMapping(path = ["/category"])
private final class CategoryController(
    private val categoryService: CategoryService,
){
    @PostMapping
    private final suspend fun add (
        @Validated(value = [CategoryDto.Add::class]) @RequestBody categoryDto: CategoryDto,
    ) : Result<Boolean> {
        // coroutineContext 数据上下文传递
        val context: CoroutineContext? = coroutineContext
        val id: Int = context?.get(UserContext)?.id ?: 0
        val isSuccess: Boolean = categoryService.add(categoryDto = categoryDto.copy(createUser = id.toUInt())).await()
        return if (isSuccess) {
            Result.success(message = "success", data = true)
        } else {
            Result.failure(message = "failed", data = false)
        }
    }
    @GetMapping
    private final suspend fun list (): Result<List<Category>> {
        val context: CoroutineContext? = coroutineContext
        val id: Int = context?.get(UserContext)?.id ?: 0
        val categories: List<Category> = categoryService.list(userId = id.toUInt()).await()
        if (categories.isEmpty()) {
            return Result.failure(message = "failed", data = emptyList())
        }
        return Result.success(message = "success", data = categories)
    }
    @GetMapping(value = ["/detail"])
    private final suspend fun detail (
        @RequestParam(value = "id") id: Int,
    ): Result<Category> {
        val category: Category = categoryService.detail(categoryId = id.toUInt()).await()
        return if (category.id <= 0U){
            Result.failure(message = "failed", data = category)
        } else {
            Result.success(message = "success", data = category)
        }
    }
    @PutMapping(value = ["/update"])
    private final suspend fun update (
        @Validated(value = [CategoryDto.Update::class]) @RequestBody categoryDto: CategoryDto,
    ): Result<Boolean> {
        val isSuccess: Boolean = categoryService.update(categoryDto = categoryDto).await()
        return if (isSuccess){
            Result.success(message = "success", data = true)
        } else {
            Result.failure(message = "failed", data = false)
        }
    }
}