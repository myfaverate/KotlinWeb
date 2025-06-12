package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dao.ArticleDao
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import edu.tyut.spring_boot_ssm.entity.ArticleEntity
import edu.tyut.spring_boot_ssm.entity.CategoryEntity
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.r2dbc.Query
import org.jetbrains.exposed.v1.r2dbc.andWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.springframework.stereotype.Repository

@Repository //
internal class ArticleDaoImpl internal constructor() : ArticleDao {

    override suspend fun add(articleDto: ArticleDto): Boolean {
        val id: UInt = ArticleEntity.insert { updateBuilder: UpdateBuilder<*> ->
            updateBuilder[ArticleEntity.title] = articleDto.title!!
            updateBuilder[ArticleEntity.content] = articleDto.content!!
            updateBuilder[ArticleEntity.coverImg] = articleDto.coverImg!!
            updateBuilder[ArticleEntity.state] = articleDto.state
            updateBuilder[ArticleEntity.categoryId] = articleDto.categoryId!!
            updateBuilder[ArticleEntity.createUser] = articleDto.createUser!!
            updateBuilder[ArticleEntity.createTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
            updateBuilder[ArticleEntity.updateTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
            updateBuilder[ArticleEntity.fkArticleIdUser] = EntityID(
                id = articleDto.createUser, table = UserEntity
            )
            updateBuilder[ArticleEntity.fkArticleIdCategory] = EntityID(
                id = articleDto.categoryId, table = CategoryEntity
            )
        }.getOrNull(column = ArticleEntity.id)?.value ?: 0U
        return id > 0U
    }

    override suspend fun list(
        pageIndex: Int,
        pageSize: Int,
        categoryId: Int?,
        state: String?
    ): List<Article> {
        val queue: Query = ArticleEntity.selectAll()
        categoryId?.let {
            queue.andWhere { ArticleEntity.categoryId eq it.toUInt() }
        }
        state?.let {
            queue.andWhere { ArticleEntity.state eq it }
        }
        return queue
            .limit(count = pageSize)
            .offset(start = (pageIndex - 1) * pageSize.toLong()).map { resultRow: ResultRow ->
                Article(
                    id = resultRow[ArticleEntity.id].value,
                    title = resultRow[ArticleEntity.title],
                    content = resultRow[ArticleEntity.content],
                    coverImg = resultRow[ArticleEntity.coverImg],
                    state = resultRow[ArticleEntity.state],
                    categoryId = resultRow[ArticleEntity.categoryId],
                    createUser = resultRow[ArticleEntity.createUser],
                    createTime = resultRow[ArticleEntity.createTime],
                    updateTime = resultRow[ArticleEntity.updateTime],
                )
            }.toList()
    }
}