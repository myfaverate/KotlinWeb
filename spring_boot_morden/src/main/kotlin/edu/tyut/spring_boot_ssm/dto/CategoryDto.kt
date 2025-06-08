package edu.tyut.spring_boot_ssm.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@ConsistentCopyVisibility
internal data class CategoryDto internal constructor(
    @field:NotEmpty(message = "The category name cannot be empty")
    internal val categoryName: String?,
    @field:NotEmpty(message = "The category alias cannot be empty")
    internal val categoryAlias: String?,
    internal val createUser: UInt = 0U
)
