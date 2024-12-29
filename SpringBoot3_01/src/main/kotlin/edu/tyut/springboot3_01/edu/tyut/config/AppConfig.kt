package edu.tyut.springboot3_01.edu.tyut.config

import edu.tyut.springboot3_01.edu.tyut.bean.User
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@EnableConfigurationProperties(value = [User::class])
@Configuration
class AppConfig {
    // @ConfigurationProperties(prefix = "user")
    // @Bean

    fun getUser(): User {
        return User()
    }
}