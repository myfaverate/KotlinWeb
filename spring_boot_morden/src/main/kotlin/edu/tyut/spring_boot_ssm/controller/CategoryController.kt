package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.context.UserContext
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import edu.tyut.spring_boot_ssm.service.CategoryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
        @Valid @RequestBody categoryDto: CategoryDto,
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
}