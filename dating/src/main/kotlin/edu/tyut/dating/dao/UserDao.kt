package edu.tyut.dating.dao

import edu.tyut.dating.bean.exposed.User

internal interface UserDao {
    fun findAll(): List<User>
    fun getUserById(id: Int): User
}