package edu.tyut.spring_boot_ssm.bean

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@ConsistentCopyVisibility
internal data class User internal constructor(
    @field:NotNull(message = "id must not be null")
    internal val id: UInt? = 0U, // internal val id: UInt?,
    internal val username: String = "",
    @JsonIgnore
    internal val password: String = "",
    @field:NotNull(message = "password not null")
    @field:Size(min = 1, max = 10, message = "username must have at least 1 character")
    internal val nickname: String = "",
    internal val userPicture: String = "",
    @field:NotNull(message = "email must not be null")
    @field:Email(message = "email must be valid")
    internal val email: String = "",
    internal val createTime: LocalDateTime = Clock.System.now().toLocalDateTime(
        timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
    ),
    internal val updateTime: LocalDateTime = Clock.System.now().toLocalDateTime(
        timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
    ),
)