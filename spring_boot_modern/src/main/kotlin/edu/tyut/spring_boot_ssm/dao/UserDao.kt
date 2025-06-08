package edu.tyut.spring_boot_ssm.dao

import edu.tyut.spring_boot_ssm.bean.User
import org.eclipse.jetty.util.security.Password

internal interface UserDao {
    suspend fun isUserExists(
        username: String
    ): Boolean
    suspend fun addUser(
        username: String,
        password: String,
    ): Boolean
    suspend fun findUserByUsername(username: String): User
    suspend fun updateUser(user: User): Boolean
    suspend fun updateAvatar(id: UInt, avatarUrl: String): Boolean
    suspend fun updatePassword(id: UInt, password: String): Boolean
}