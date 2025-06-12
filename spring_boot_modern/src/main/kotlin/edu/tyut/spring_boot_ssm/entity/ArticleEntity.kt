package edu.tyut.spring_boot_ssm.entity

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.UIntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

internal object ArticleEntity : UIntIdTable(name = "article") {
    internal val title: Column<String> = varchar(name = "title", length = 30)
    internal val content: Column<String> = varchar(name = "content", length = 10000)
    internal val coverImg: Column<String> = varchar(name = "cover_img", length = 128)
    internal val state: Column<String> = varchar(name = "state", length = 10)
        .check { column: Column<String> ->
            column inList listOf("Draft", "Publish")
        }
    internal val categoryId: Column<UInt> = uinteger(name = "category_id")
    internal val createUser: Column<UInt> = uinteger(name = "create_user")
    internal val createTime: Column<LocalDateTime> = datetime(name = "create_time")
    internal val updateTime: Column<LocalDateTime> = datetime(name = "update_time")
    internal val fkArticleIdUser:  Column<EntityID<UInt>> = reference(name = "fk_article_user", foreign = UserEntity, onDelete = ReferenceOption.CASCADE)
    internal val fkArticleIdCategory:  Column<EntityID<UInt>> = reference(name = "fk_article_category", foreign = CategoryEntity, onDelete = ReferenceOption.CASCADE)
}