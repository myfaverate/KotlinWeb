package edu.tyut.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@Configuration
@ComponentScan(value = ["edu.tyut.controller"])
internal open class MvcConfig : CoroutinesWebMvcConfigurer()