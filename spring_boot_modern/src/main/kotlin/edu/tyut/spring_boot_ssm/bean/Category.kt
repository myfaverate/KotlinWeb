package edu.tyut.spring_boot_ssm.bean

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import edu.tyut.spring_boot_ssm.serializer.KtxLocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime

@ConsistentCopyVisibility
internal data class Category internal constructor(
    internal val id: UInt,
    internal val categoryName: String,
    internal val categoryAlias: String,
    internal val createUser: UInt,
    @field:JsonSerialize(using = KtxLocalDateTimeSerializer::class)
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    internal val createTime: LocalDateTime,
    @field:JsonSerialize(using = KtxLocalDateTimeSerializer::class)
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    internal val updateTime: LocalDateTime,
)
