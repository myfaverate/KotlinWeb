package edu.tyut.bean

import kotlinx.serialization.Serializable

@Serializable
@ConsistentCopyVisibility
internal data class Person internal constructor(
    private val name: String,
    private val age: Int,
    private val gender: String,
)

// @Serializable
// data class Person (
//     val name: String,
//     val age: Int,
//     val gender: String,
// )