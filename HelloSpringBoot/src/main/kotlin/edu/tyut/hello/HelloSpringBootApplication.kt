package edu.tyut.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootApplication>(*args)
}
