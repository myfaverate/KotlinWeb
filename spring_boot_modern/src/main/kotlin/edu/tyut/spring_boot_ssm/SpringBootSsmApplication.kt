package edu.tyut.spring_boot_ssm

import edu.tyut.spring_boot_ssm.hints.ReflectHints
import edu.tyut.spring_boot_ssm.hints.ResourceHints
import kotlinx.coroutines.coroutineScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ResourceHints::class, ReflectHints::class])
@SpringBootApplication
private class SpringBootSsmApplication

private val logger: Logger = LoggerFactory.getLogger(SpringBootSsmApplication::class.java)

internal suspend fun main(args: Array<String>): Unit = coroutineScope {
    logger.info("args: ${args.joinToString()}")
    val isDev = true
    System.setProperty(kotlinx.coroutines.DEBUG_PROPERTY_NAME, if (isDev) kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON else kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF)
    runApplication<SpringBootSsmApplication>(*args)
}
