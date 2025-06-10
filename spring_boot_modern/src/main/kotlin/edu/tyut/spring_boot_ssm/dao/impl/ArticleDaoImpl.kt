package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dao.ArticleDao
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import edu.tyut.spring_boot_ssm.entity.ArticleEntity
import edu.tyut.spring_boot_ssm.entity.CategoryEntity
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.coroutines.Deferred
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.r2dbc.insert
import org.springframework.stereotype.Repository

@Repository //
internal class ArticleDaoImpl internal constructor(): ArticleDao {
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
            updateBuilder[ArticleEntity.fkArticleIdUser] =  EntityID(
                id = articleDto.createUser, table = UserEntity)
            updateBuilder[ArticleEntity.fkArticleIdCategory] =  EntityID(
                id = articleDto.categoryId, table = CategoryEntity)
        }.getOrNull(column = ArticleEntity.id)?.value ?: 0U
        return id > 0U
    }
}