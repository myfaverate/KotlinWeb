package edu.tyut.dating

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
private class DatingApplication

internal fun main(args: Array<String>) {
	runApplication<DatingApplication>(args = args)
}