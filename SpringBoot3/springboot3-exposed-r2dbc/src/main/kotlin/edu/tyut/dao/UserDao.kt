package edu.tyut.dao

import edu.tyut.bean.User
import kotlinx.coroutines.Deferred

internal interface UserDao {
    suspend fun findUserById(id: Int): User
    suspend fun insetUser(user: User): Boolean
}