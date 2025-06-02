package edu.tyut.bean

@ConsistentCopyVisibility
internal data class Student internal constructor(
    var id: Int = 0,
    var name: String = "",
    var gender: String = "",
    var age: Int = 0,
    var `class`: String = "",
)
