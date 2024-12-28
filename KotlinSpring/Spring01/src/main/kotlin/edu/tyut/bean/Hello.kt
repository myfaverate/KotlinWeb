package edu.tyut.bean

import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Hello(
    val name: String = "World",
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    fun hello() {
        logger.info("Hello ${this.name}")
    }
}