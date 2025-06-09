package edu.tyut.dating

import edu.tyut.dating.hints.ReflectHints
import kotlinx.coroutines.coroutineScope
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ReflectHints::class])
@SpringBootApplication
private class DatingApplication

internal suspend fun main(args: Array<String>): Unit = coroutineScope {
    runApplication<DatingApplication>(args = args)
}