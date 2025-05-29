package edu.tyut.spring_boot_reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootReactiveApplication

fun main(args: Array<String>) {
    runApplication<SpringBootReactiveApplication>(*args)
}
