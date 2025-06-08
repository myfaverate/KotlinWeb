package edu.tyut.spring_boot_ssm.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.groups.Default

@ConsistentCopyVisibility
internal data class CategoryDto internal constructor(
    @field:NotNull(message = "id not null", groups = [Update::class])
    internal val id: UInt?,
    // @field:NotEmpty(message = "The category name cannot be empty", groups = [Add::class, Update::class])
    @field:NotEmpty(message = "The category name cannot be empty")
    internal val categoryName: String?,
    // @field:NotEmpty(message = "The category alias cannot be empty", groups = [Add::class, Update::class])
    @field:NotEmpty(message = "The category alias cannot be empty")
    internal val categoryAlias: String?,
    internal val createUser: UInt = 0U
) {
    internal interface Add : Default
    internal interface Update: Default
}
/*
Update group:
id not null
The category name cannot be empty
The category alias cannot be empty
Default group:
The category name cannot be empty
The category alias cannot be empty
Add group:
The category name cannot be empty
The category alias cannot be empty
 */
