package edu.tyut.dao

import edu.tyut.bean.User

interface UserDao {
    fun insertUser(user: User): Int
    fun getUserById(id: Int): User?
    fun getAllUsers(): List<User>
    fun login(username: String, password: String, age: Int): User?
    fun getCount(): Int
    fun getUsersByLike(usernameLike: String): List<User>
}