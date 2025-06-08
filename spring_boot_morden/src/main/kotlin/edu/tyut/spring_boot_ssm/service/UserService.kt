package edu.tyut.spring_boot_ssm.service

import edu.tyut.spring_boot_ssm.bean.User
import kotlinx.coroutines.Deferred

internal interface UserService {
    suspend fun isUserExists(
        username: String
    ): Deferred<Boolean>
    suspend fun register(
        username: String,
        password: String,
    ): Deferred<Boolean>
    suspend fun findUserByUsername(username: String): Deferred<User>
    suspend fun updateUser(user: User): Deferred<Boolean>
    suspend fun updateAvatar(id: UInt, avatarUrl: String): Deferred<Boolean>
    suspend fun updatePassword(id: UInt, password: String): Deferred<Boolean>
}