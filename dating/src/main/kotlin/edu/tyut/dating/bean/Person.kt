package edu.tyut.dating.bean

import kotlinx.serialization.Serializable

@Serializable
internal data class Person(
    internal val name: String,
    internal val age: Int,
    internal val gender: String,
)
