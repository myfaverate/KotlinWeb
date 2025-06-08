package edu.tyut.spring_boot_ssm.bean

import kotlinx.datetime.LocalDateTime

@ConsistentCopyVisibility
internal data class Article internal constructor(
    internal val id: UInt,
    internal val title: String,
    internal val content: String,
    internal val coverImg: String,
    internal val state: String,
    internal val categoryId: UInt,
    internal val createUser: UInt,
    internal val createTime: LocalDateTime,
    internal val updateTime: LocalDateTime,
)
