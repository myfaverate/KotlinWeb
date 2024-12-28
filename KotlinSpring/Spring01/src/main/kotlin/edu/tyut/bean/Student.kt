package edu.tyut.bean

import edu.tyut.edu.tyut.annotation.NoArg

@NoArg
data class Student(
    val sid: Int,
    val name: String,
    val age: Int,
    val gender: String,
)
