package edu.tyut.bean

import kotlinx.serialization.Serializable

@Serializable
data class SysSchedule(
    val sid: Int,
    val uid: Int,
    val title: String,
    val completed: Int,
)