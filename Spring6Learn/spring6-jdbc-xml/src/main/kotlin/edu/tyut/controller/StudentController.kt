package edu.tyut.controller

import edu.tyut.bean.Student
import edu.tyut.service.StudentService

internal class StudentController(
    private val studentService: StudentService
){
    internal fun findAll(): List<Student>{
        return studentService.findAll()
    }
}