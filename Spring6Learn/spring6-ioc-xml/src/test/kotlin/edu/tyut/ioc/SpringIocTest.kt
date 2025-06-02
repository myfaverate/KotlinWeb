package edu.tyut.ioc

import edu.tyut.bean.Happy
import edu.tyut.bean.Lifecycle
import edu.tyut.factory.HappyFactoryBean
import edu.tyut.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import kotlin.test.Test

internal class SpringIocTest {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    internal fun getHappy() {
        val applicationContext: ApplicationContext = ClassPathXmlApplicationContext("spring-01.xml")
        println(applicationContext.getBean("happy"))
        println(applicationContext.getBean("happyByFactory"))
        logger.info("hello")
    }

    @Test
    internal fun getUserService() {
        ClassPathXmlApplicationContext("spring-02.xml").use { applicationContext: ApplicationContext ->
            val userService: UserService = applicationContext.getBean("userService", UserService::class.java)
            logger.info("userService: {}", userService)
        }
    }

    @Test
    internal fun getLifeCycle() {
        ClassPathXmlApplicationContext("spring-03.xml").use { applicationContext: ApplicationContext ->
            val lifecycle: Lifecycle = applicationContext.getBean("lifeCycle", Lifecycle::class.java)
            logger.info("lifeCycle: {}", lifecycle)
        }
    }

    @Test
    internal fun getFactoryBean() {
        ClassPathXmlApplicationContext("spring-04.xml").use { applicationContext: ApplicationContext ->
            val happy: Happy = applicationContext.getBean("happy", Happy::class.java)
            val happyFactoryBean: HappyFactoryBean = applicationContext.getBean("&happy", HappyFactoryBean::class.java)
            logger.info("happy: {}, happyFactoryBean: {}", happy, happyFactoryBean)
        }
    }

}