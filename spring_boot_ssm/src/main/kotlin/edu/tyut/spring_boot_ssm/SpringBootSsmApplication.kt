package edu.tyut.spring_boot_ssm

import kotlinx.coroutines.coroutineScope
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
private class SpringBootSsmApplication

internal suspend fun main(args: Array<String>): Unit = coroutineScope {
    val isDev = true
    System.setProperty(kotlinx.coroutines.DEBUG_PROPERTY_NAME, if (isDev) kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON else kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF)
    runApplication<SpringBootSsmApplication>(*args)
}
