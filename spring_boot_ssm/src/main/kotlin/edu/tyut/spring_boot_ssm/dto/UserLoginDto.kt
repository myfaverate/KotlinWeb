package edu.tyut.spring_boot_ssm.dto

import org.springframework.web.bind.annotation.BindParam

internal data class UserLoginDto(
    @BindParam(value = "username")
    internal val username: String,
    @BindParam(value = "password")
    internal val password: String
)