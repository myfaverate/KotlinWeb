package edu.tyut.service

import edu.tyut.dao.UserDao

internal class UserService(
    private val userDao: UserDao
) {
    private lateinit var name: String
    internal fun setName(name: String) {
        this.name = name
    }
}