package edu.tyut.dating.bean.exposed

import kotlinx.serialization.Serializable

@Serializable
internal data class Photo (
    internal val id: Long,
    internal val photoUrl: String,
    internal val photoName: String,
    internal val description: String,
)