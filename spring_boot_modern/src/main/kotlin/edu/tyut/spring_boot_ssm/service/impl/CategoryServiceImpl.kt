package edu.tyut.spring_boot_ssm.service.impl

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.dao.CategoryDao
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import edu.tyut.spring_boot_ssm.service.CategoryService
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.springframework.stereotype.Service

@Service
internal final class CategoryServiceImpl(
    private val categoryDao: CategoryDao
) : CategoryService {
    override suspend fun add(categoryDto: CategoryDto): Deferred<Boolean> = suspendTransactionAsync{
        categoryDao.add(categoryDto = categoryDto)
    }

    override suspend fun list(userId: UInt): Deferred<List<Category>> = suspendTransactionAsync {
        categoryDao.list(userId = userId)
    }

    override suspend fun detail(categoryId: UInt): Deferred<Category> = suspendTransactionAsync{
        categoryDao.detail(categoryId = categoryId)
    }

    override suspend fun update(categoryDto: CategoryDto): Deferred<Boolean> = suspendTransactionAsync {
        categoryDao.update(categoryDto = categoryDto)
    }
}