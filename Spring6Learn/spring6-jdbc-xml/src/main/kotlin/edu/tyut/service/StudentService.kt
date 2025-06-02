package edu.tyut.service

import edu.tyut.bean.Student

internal interface StudentService {
    fun findAll(): List<Student>
}