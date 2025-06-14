package edu.tyut

import kotlinx.coroutines.coroutineScope
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
private class Application

internal suspend fun main(args: Array<String>) : Unit = coroutineScope {
    runApplication<Application>(*args)
}
