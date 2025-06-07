package edu.tyut.spring_boot_ssm.entity

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.UIntIdTable
import org.jetbrains.exposed.v1.datetime.datetime

internal object CategoryEntity : UIntIdTable(name = "category") {
    internal val categoryName: Column<String> = varchar(name = "category_name", length = 32)
    internal val categoryAlias: Column<String> = varchar(name = "category_alias", length = 32)
    internal val createUser: Column<UInt> = uinteger(name = "create_user")
    internal val createTime: Column<LocalDateTime> = datetime(name = "create_time")
    internal val updateTime: Column<LocalDateTime> = datetime(name = "update_time")
    internal val fkCategoryIdUser = reference(name = "fk_category_user", foreign = UserEntity, onDelete = ReferenceOption.CASCADE)
}