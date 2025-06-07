package edu.tyut.spring_boot_ssm.entity

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.dao.id.UIntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

internal object UserEntity : UIntIdTable(name = "user") {
    internal val username: Column<String> = varchar(name = "username", length = 20)
    internal val password: Column<String> = varchar(name = "password", length = 32)
    internal val nickname: Column<String> = varchar(name = "nickname", length = 10)
    internal val userPicture: Column<String> = varchar(name = "user_picture", length = 128)
    internal val createTime: Column<LocalDateTime> = datetime(name = "create_time")
    internal val updateTime: Column<LocalDateTime> = datetime(name = "update_time")
}