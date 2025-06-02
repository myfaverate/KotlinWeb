package edu.tyut.dao

import edu.tyut.bean.Student

internal interface StudentDao {
    fun queryAll(): List<Student>
}