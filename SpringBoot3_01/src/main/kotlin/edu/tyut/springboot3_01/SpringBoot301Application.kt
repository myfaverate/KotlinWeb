package edu.tyut.springboot3_01

import edu.tyut.springboot3_01.edu.tyut.bean.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

private val logger: Logger = LoggerFactory.getLogger(SpringBoot301Application::class.java)

@SpringBootApplication
class SpringBoot301Application

fun main(args: Array<String>) {
    val configurableApplicationContext: ConfigurableApplicationContext = runApplication<SpringBoot301Application>(*args)
    val user: User = configurableApplicationContext.getBean(User::class.java)
    logger.info("User is {}", user)
}
