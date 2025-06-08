package edu.tyut.spring_boot_ssm.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.result.view.ViewResolver

@Configuration
internal class ViewResolverConfig(
    private val thymeleafViewResolver: ViewResolver
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Bean
    internal fun handlerStrategies(): HandlerStrategies {
        logger.info("ViewResolver: $thymeleafViewResolver")
        return HandlerStrategies.builder()
            .viewResolver(thymeleafViewResolver)
            .build()
    }
}