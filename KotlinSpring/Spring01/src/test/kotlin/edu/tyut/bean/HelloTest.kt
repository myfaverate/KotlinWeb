package edu.tyut.bean

import edu.tyut.edu.tyut.controller.UserController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.support.ClassPathXmlApplicationContext
import kotlin.test.Test

class HelloTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun testHello() {
        val applicationContext = ClassPathXmlApplicationContext("applicationContext.xml")
        val student: Student = applicationContext.getBean("studentOne", Student::class.java)
        logger.info("student: $student")
    }

    @Test
    fun testController(){
        val applicationContext = ClassPathXmlApplicationContext("applicationContext.xml")
        val userController: UserController = applicationContext.getBean(UserController::class.java)
        userController.saveUser()
    }
}