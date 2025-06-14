package edu.tyut.spring_boot_ssm.service

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import kotlinx.coroutines.Deferred

internal interface CategoryService {
    suspend fun add (
        categoryDto: CategoryDto,
    ): Deferred<Boolean>
    suspend fun list(
        userId: UInt,
    ): Deferred<List<Category>>
    suspend fun detail(
        categoryId: UInt,
    ): Deferred<Category>
    suspend fun update(
        categoryDto: CategoryDto
    ): Deferred<Boolean>
}