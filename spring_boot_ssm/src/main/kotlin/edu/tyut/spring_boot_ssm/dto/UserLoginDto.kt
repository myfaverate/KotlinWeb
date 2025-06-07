package edu.tyut.spring_boot_ssm.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.BindParam

// TODO
internal data class UserLoginDto(
    @field:NotBlank(message = "username not null")
    @field:Pattern(regexp = "^\\S{5,16}$")
    @BindParam(value = "userName")
    internal val username: String,
    @field:Pattern(regexp = "^\\S{5,16}$")
    @BindParam(value = "passWord")
    internal val password: String
)