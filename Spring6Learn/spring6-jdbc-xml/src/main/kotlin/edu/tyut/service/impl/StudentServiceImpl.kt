package edu.tyut.service.impl

import edu.tyut.bean.Student
import edu.tyut.dao.impl.StudentDaoImpl
import edu.tyut.service.StudentService

internal class StudentServiceImpl(
    private val studentDao: StudentDaoImpl
) : StudentService {
    override fun findAll(): List<Student> {
        return studentDao.queryAll()
    }
}