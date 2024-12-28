package edu.tyut.aspect

import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.jdbc.core.JdbcTemplate
import kotlin.test.Test

class AopTest {

    @Test
    fun aspectjTest(){
        val applicationContext = ClassPathXmlApplicationContext("applicationContext.xml")
        val person = applicationContext.getBean(Person::class.java)
        person.eat("zsdh", 12)
    }
}