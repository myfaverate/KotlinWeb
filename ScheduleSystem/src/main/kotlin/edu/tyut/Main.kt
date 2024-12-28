package edu.tyut

import edu.tyut.bean.Result
import edu.tyut.bean.SysUser
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence

fun main() {
    val user = SysUser(1, "2", "3")
    println(Json.encodeToString(value = Result.success("1", user)))
    println(Json.decodeFromString<Result.Success<SysUser>>(Json.encodeToString(value = Result.success("1", user))))
    println(Json.encodeToString(value = Result.failure("1", user)))
    println(Json.decodeFromString<Result.Failure<SysUser>>(Json.encodeToString(value = Result.failure("1", user))))
}