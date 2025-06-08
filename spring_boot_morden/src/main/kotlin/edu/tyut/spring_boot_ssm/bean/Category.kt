package edu.tyut.spring_boot_ssm.bean

import kotlinx.datetime.LocalDateTime

@ConsistentCopyVisibility
internal data class Category internal constructor(
    internal val categoryName: String,
    internal val categoryAlias: String,
    internal val createUser: UInt,
    internal val createTime: LocalDateTime,
    internal val updateTime: LocalDateTime,
)
