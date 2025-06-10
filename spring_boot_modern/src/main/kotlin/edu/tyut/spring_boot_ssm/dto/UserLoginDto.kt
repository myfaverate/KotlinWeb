package edu.tyut.spring_boot_ssm.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.BindParam

// TODO
internal data class UserLoginDto(
    @field:NotNull(message = "username not null")
    @field:NotBlank(message = "username not blank")
    @field:Pattern(regexp = "^\\S{5,16}$", message = "username must have 5 ~ 6 character")
    @field:Size(min = 5, max = 16, message = "username must have 5 ~ 6 character")
    @BindParam(value = "userName")
    internal val username: String?,
    @field:NotNull(message = "password not null")
    @field:NotBlank(message = "password not blank")
    @field:Pattern(regexp = "^\\S{5,16}$", message = "password must have 5 ~ 6 characters")
    @field:Size(min = 5, max = 16, message = "password must have 5 ~ 6 character")
    @BindParam(value = "passWord")
    internal val password: String?
)