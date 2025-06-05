package edu.tyut.bean

import kotlinx.serialization.Serializable

@Serializable
@ConsistentCopyVisibility
internal data class Employee internal constructor(
    internal val id: Int,
    internal val empName: String,
    internal val age: Int,
    internal val gender: Char,
    internal val email: String
)