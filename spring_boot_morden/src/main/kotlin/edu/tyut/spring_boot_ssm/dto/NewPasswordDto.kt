package edu.tyut.spring_boot_ssm.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.BindParam

// TODO
internal data class NewPasswordDto(
    @field:NotNull(message = "oldPassword not null")
    @field:NotBlank(message = "oldPassword not blank")
    @field:Pattern(regexp = "^\\S{5,16}$", message = "oldPassword must has 5~16 characters")
    @field:Size(min = 5, max = 16, message = "oldPassword must has 5~16 character")
    @BindParam(value = "oldPassword")
    internal val oldPassword: String?,
    @field:NotNull(message = "newPassword not null")
    @field:NotBlank(message = "newPassword not blank")
    @field:Pattern(regexp = "^\\S{5,16}$", message = "newPassword must has 5~16 characters")
    @field:Size(min = 5, max = 16, message = "newPassword must has 5~16 character")
    @BindParam(value = "newPassword")
    internal val newPassword: String?,
    @field:NotNull(message = "confirmPassword not null")
    @field:NotBlank(message = "confirmPassword not blank")
    @field:Pattern(regexp = "^\\S{5,16}$", message = "confirmPassword must has 5~16 characters")
    @field:Size(min = 5, max = 16, message = "confirmPassword must has 5~16 character")
    @BindParam(value = "confirmPassword")
    internal val confirmPassword: String?
)