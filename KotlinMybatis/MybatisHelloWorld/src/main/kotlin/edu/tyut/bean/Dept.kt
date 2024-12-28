package edu.tyut.bean

import edu.tyut.annotation.DataClassOpen

@DataClassOpen
data class Dept(
    val deptId: Int,
    val deptName: String,
    val empList: MutableList<Emp>
)