package edu.tyut

import edu.tyut.bean.EmailUser
import edu.tyut.hints.ReflectHints
import edu.tyut.hints.ResourceHints
import kotlinx.coroutines.coroutineScope
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ResourceHints::class, ReflectHints::class])
@SpringBootApplication
@EnableConfigurationProperties(value = [EmailUser::class])
private class Application

internal suspend fun main(args: Array<String>) : Unit = coroutineScope {
    runApplication<Application>(*args)
}
