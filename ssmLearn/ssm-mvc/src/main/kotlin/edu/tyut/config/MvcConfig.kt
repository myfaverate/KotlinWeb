package edu.tyut.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(value = ["edu.tyut.controller"])
internal open class MvcConfig : CoroutinesWebMvcConfigurer() {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    init {
        logger.info("Starting MVC config...")
    }

}