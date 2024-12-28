package edu.tyut.aspect

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class PersonImpl : Person {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun eat(name: String, age: Int): String {
        logger.info("Person eat...")
        return "Person(name: $name, age: $age)"
    }
}