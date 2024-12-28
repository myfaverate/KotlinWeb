package edu.tyut.bean

import edu.tyut.annotation.DataClassOpen

@DataClassOpen
data class Emp(
    val empId: Int,
    val empName: String,
    val age: Int,
    val gender: String,
    val dept: Dept,
)