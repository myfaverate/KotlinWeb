
package edu.tyut.dao.impl

import edu.tyut.bean.Student
import edu.tyut.dao.StudentDao
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

@Suppress("SqlNoDataSourceInspection")
internal class StudentDaoImpl(
    private val jdbcTemplate: JdbcTemplate
) : StudentDao {
    override fun queryAll(): List<Student> {
        val students: List<Student> = jdbcTemplate.query<Student>("select * from students", BeanPropertyRowMapper<Student>(Student::class.java))
        return students
    }
}