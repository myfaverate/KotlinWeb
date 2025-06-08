package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.bean.Category
import edu.tyut.spring_boot_ssm.dao.CategoryDao
import edu.tyut.spring_boot_ssm.dto.CategoryDto
import edu.tyut.spring_boot_ssm.entity.CategoryEntity
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.core.statements.UpdateStatement
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.update
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

    override suspend fun list(userId: UInt): List<Category> {
        return CategoryEntity.selectAll().map { resultRow: ResultRow ->
            Category(
                id = resultRow[CategoryEntity.id].value,
                categoryName = resultRow[CategoryEntity.categoryName],
                categoryAlias = resultRow[CategoryEntity.categoryAlias],
                createUser = resultRow[CategoryEntity.createUser],
                updateTime = resultRow[CategoryEntity.updateTime],
                createTime = resultRow[CategoryEntity.createTime],
            )
        }.toList()
    }

    override suspend fun detail(categoryId: UInt): Category {
         val category: Category = CategoryEntity.selectAll().where { CategoryEntity.id eq categoryId }.firstOrNull()?.let { resultRow ->
             Category(
                 id = resultRow[CategoryEntity.id].value,
                 categoryName = resultRow[CategoryEntity.categoryName],
                 categoryAlias = resultRow[CategoryEntity.categoryAlias],
                 createUser = resultRow[CategoryEntity.createUser],
                 updateTime = resultRow[CategoryEntity.updateTime],
                 createTime = resultRow[CategoryEntity.createTime],
             )
         } ?: Category(
             id = 0U,
             categoryName = "",
             categoryAlias = "",
             createUser = 0U,
             updateTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai")),
             createTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
         )
        return category
    }

    override suspend fun update(categoryDto: CategoryDto): Boolean {
        val rows: Int = CategoryEntity.update(where = { CategoryEntity.id eq categoryDto.id }) { updateStatement: UpdateStatement ->
            updateStatement[CategoryEntity.categoryName] = categoryDto.categoryName!!
            updateStatement[CategoryEntity.categoryAlias] = categoryDto.categoryAlias!!
            updateStatement[CategoryEntity.updateTime] = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
        }
        return rows > 0
    }

}