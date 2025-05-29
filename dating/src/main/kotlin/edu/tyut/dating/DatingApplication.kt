package edu.tyut.dating

import edu.tyut.dating.exposed.ExposedHints
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.ImportRuntimeHints

@ImportRuntimeHints(value = [ExposedHints::class])
@SpringBootApplication
private class DatingApplication

internal fun main(args: Array<String>) {
	runApplication<DatingApplication>(args = args)
}