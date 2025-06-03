package edu.tyut.bean

@ConsistentCopyVisibility
internal data class User private constructor(
    private val name: String,
    private val age: Int,
)