package edu.tyut.springboot3_01.edu.tyut.bean

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "user")
// @Component
data class User(
    var username: String = "",
    var password: String = "",
    var age: Int = 0,
    var salary: Int = 0,
)
