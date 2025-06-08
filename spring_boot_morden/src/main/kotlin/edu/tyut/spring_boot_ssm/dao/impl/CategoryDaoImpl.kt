package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.dao.CategoryDao
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import edu.tyut.spring_boot_ssm.entity.CategoryEntity
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.r2dbc.insert
import org.springframework.stereotype.Repository

@Repository //
internal class CategoryDaoImpl : CategoryDao {
    override suspend fun add(categoryDto: CategoryDto): Boolean {
        val id: UInt = CategoryEntity.insert { updateBuilder: UpdateBuilder<*> ->
            updateBuilder[CategoryEntity.categoryName] = categoryDto.categoryName!!
            updateBuilder[CategoryEntity.categoryAlias] = categoryDto.categoryAlias!!
            updateBuilder[CategoryEntity.createUser] = categoryDto.createUser
            updateBuilder[CategoryEntity.fkCategoryIdUser] = EntityID(
                id = categoryDto.createUser, table = UserEntity)
            updateBuilder[CategoryEntity.createTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
            updateBuilder[CategoryEntity.updateTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
        }.getOrNull(column = CategoryEntity.id)?.value ?: 0U
        return id > 0U
    }
}