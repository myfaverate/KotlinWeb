package edu.tyut.spring_boot_ssm.dao

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import kotlinx.coroutines.Deferred

internal interface CategoryDao {
    suspend fun add(
        categoryDto: CategoryDto,
    ): Boolean
}