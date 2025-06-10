package edu.tyut.spring_boot_ssm.dto

import edu.tyut.spring_boot_ssm.annotation.State
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

// TODO
@ConsistentCopyVisibility
internal data class ArticleDto internal constructor(
    @field:NotEmpty(message = "title must be not empty")
    @field:Size(min = 1, max = 10, message = "title must have 1 ~ 10 characters")
    internal val title: String?,
    @field:NotEmpty(message = "content must be not empty")
    internal val content: String?,
    @field:NotEmpty(message = "coverImg must be not empty")
    @field:URL(message = "coverImg must be a valid url")
    internal val coverImg: String?,
    @field:State(message = "state must be Draft or Publish")
    internal val state: String,
    @field:NotNull(message = "categoryId must be not null")
    internal val categoryId: UInt?,
    internal val createUser: UInt?,
)