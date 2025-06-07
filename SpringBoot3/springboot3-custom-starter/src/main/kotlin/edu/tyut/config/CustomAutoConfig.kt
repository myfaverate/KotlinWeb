package edu.tyut.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanFactory
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigurationPackages
import org.springframework.context.annotation.Bean

private const val BASE_PACKAGE: String = "basePackage"

/**
 * custom config
 */
@AutoConfiguration
internal open class CustomAutoConfig {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Bean(value = [BASE_PACKAGE])
    internal open fun getBasePackage (
        beanFactory: BeanFactory
    ): String {
        val packages: List<String> = AutoConfigurationPackages.get(beanFactory)
        logger.info("CustomAutoConfig AutoConfiguration Packages: $packages")
        return packages.firstOrNull() ?: "edu.tyut"
    }
}