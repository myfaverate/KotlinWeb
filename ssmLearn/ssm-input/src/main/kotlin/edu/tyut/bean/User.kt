package edu.tyut.bean

@ConsistentCopyVisibility
internal data class User internal constructor(
    private val name: String,
    private val age: Int,
)