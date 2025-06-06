package edu.tyut

import edu.tyut.bean.EmailUser
import edu.tyut.hints.ResourceHints
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ResourceHints::class])
@SpringBootApplication
@EnableConfigurationProperties(value = [EmailUser::class])
private class Application

internal fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
