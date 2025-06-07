package edu.tyut.spring_boot_ssm.entity

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.dao.id.UIntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

internal object ArticleEntity : UIntIdTable(name = "article") {
    internal val title: Column<String> = varchar(name = "title", length = 30)
    internal val content: Column<String> = varchar(name = "content", length = 10000)
}