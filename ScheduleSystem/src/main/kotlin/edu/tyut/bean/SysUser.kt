package edu.tyut.bean

import edu.tyut.util.ColumnName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SysUser(
    val uid: Int = 0,
    val username: String,
    @ColumnName(value = "user_pwd")
    @SerialName(value = "password")
    var password: String,
)