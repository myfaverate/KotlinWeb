package edu.tyut.service

import edu.tyut.bean.User
import kotlinx.coroutines.Deferred

internal interface UserService {
    suspend fun findUserById(id: Int): Deferred<User>
}