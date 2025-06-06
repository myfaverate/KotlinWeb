package edu.tyut.dao

import edu.tyut.bean.User
import kotlinx.coroutines.Deferred

internal interface UserDao {
    suspend fun findUserById(id: Int): Deferred<User>
}