package edu.tyut.spring_boot_ssm.service

import kotlinx.coroutines.Deferred

internal interface UserService {
    suspend fun isUserExists(
        username: String
    ): Deferred<Boolean>
    suspend fun register(
        username: String,
        password: String,
    ): Deferred<Boolean>
}