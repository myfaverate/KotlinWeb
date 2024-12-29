package edu.tyut.springboot_01

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBoot01Application

fun main(args: Array<String>) {
    runApplication<SpringBoot01Application>(*args)
}
