package edu.tyut.spring_boot_ssm.dto

// TODO
@ConsistentCopyVisibility
internal data class ArticleDto internal constructor(
    internal val title: String,
    internal val content: String,
    internal val coverImg: String,
    internal val state: String,
    internal val categoryId: UInt,
    internal val createUser: UInt?,
)
