package edu.tyut.jdbc

import edu.tyut.bean.Student
import edu.tyut.controller.StudentController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import kotlin.test.Test

@Suppress("SqlNoDataSourceInspection")
internal class JdbcTemplateTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    internal fun testForIoc() {
        ClassPathXmlApplicationContext("spring-01.xml").use { applicationContext: ApplicationContext ->
            val jdbcTemplate: JdbcTemplate = applicationContext.getBean(JdbcTemplate::class.java)
            // val sql: String = """
            //     insert into students(name, gender, age, class) values (?, ?, ?, ?)
            // """.trimIndent()
            // val rows: Int = jdbcTemplate.update(sql, "二狗子", "男", 18, "三年二班")
            // logger.info("rows: {}", rows)
            // val student: Student? = jdbcTemplate.queryForObject(
            //     "select * from students where id = ?",
            //     { result: ResultSet, rowNum: Int ->
            //         return@queryForObject Student(
            //             id = result.getInt("id"),
            //             name = result.getString("name"),
            //             gender = result.getString("gender"),
            //             age = result.getInt("age"),
            //             `class` = result.getString("class"),
            //         )
            //     },
            //     1
            // )
            // logger.info("rows: {}", student)
            val students: List<Student> = jdbcTemplate.query<Student>(
                "select * from students",
                BeanPropertyRowMapper<Student>(Student::class.java)
            )
            logger.info("students: {}", students)
        }
    }

    @Test
    internal fun testForController() {
        ClassPathXmlApplicationContext("spring-01.xml").use { applicationContext: ApplicationContext ->
            val studentController: StudentController = applicationContext.getBean("studentController", StudentController::class.java)
            val students: List<Student> = studentController.findAll()
            logger.info("testForController students: {}", students)
        }
    }
}