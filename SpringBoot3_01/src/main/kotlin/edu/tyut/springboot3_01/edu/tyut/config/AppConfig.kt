package edu.tyut.springboot3_01.edu.tyut.config

import edu.tyut.springboot3_01.edu.tyut.bean.User
import edu.tyut.springboot3_01.edu.tyut.converter.YamlHttpMessageConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.yaml.MappingJackson2YamlHttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableConfigurationProperties(value = [User::class])
@Configuration
class AppConfig : WebMvcConfigurer {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    // @ConfigurationProperties(prefix = "user")
    // @Bean
    fun getUser(): User {
        return User()
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        converters.add(YamlHttpMessageConverter())
        converters.removeIf { it is MappingJackson2YamlHttpMessageConverter }
        logger.info("configureMessageConverters: {}", converters)
    }
}