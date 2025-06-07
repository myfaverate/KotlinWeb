package edu.tyut.spring_boot_ssm.bean

import kotlinx.datetime.LocalDateTime

@ConsistentCopyVisibility
internal data class User internal constructor(
    internal val id: UInt,
    internal val username: String,
    internal val password: String,
    internal val nickname: String,
    internal val userPicture: String,
    internal val createTime: LocalDateTime,
    internal val updateTime: LocalDateTime,
)