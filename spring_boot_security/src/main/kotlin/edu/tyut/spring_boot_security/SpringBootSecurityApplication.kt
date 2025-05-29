package edu.tyut.spring_boot_security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootSecurityApplication

fun main(args: Array<String>) {
    runApplication<SpringBootSecurityApplication>(*args)
}
