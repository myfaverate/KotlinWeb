package edu.tyut

import edu.tyut.bean.EmailUser
import edu.tyut.hints.ReflectHints
import edu.tyut.hints.ResourceHints
import kotlinx.coroutines.coroutineScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ResourceHints::class, ReflectHints::class])
@SpringBootApplication
@EnableConfigurationProperties(value = [EmailUser::class])
private class Application
private val logger: Logger = LoggerFactory.getLogger(Application::class.java)
internal suspend fun main(args: Array<String>) : Unit = coroutineScope {
    logger.info("args: ${args.joinToString()}")
    val isDev = true
    System.setProperty(kotlinx.coroutines.DEBUG_PROPERTY_NAME, if (isDev) kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON else kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF)
    runApplication<Application>(*args)
}
