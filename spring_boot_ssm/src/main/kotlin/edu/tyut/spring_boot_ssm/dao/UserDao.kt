package edu.tyut.spring_boot_ssm.dao

internal interface UserDao {
    suspend fun isUserExists(
        username: String
    ): Boolean
    suspend fun addUser(
        username: String,
        password: String,
    ): Boolean
}