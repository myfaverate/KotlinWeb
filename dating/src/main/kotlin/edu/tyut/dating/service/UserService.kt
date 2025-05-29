package edu.tyut.dating.service

import edu.tyut.dating.bean.exposed.User


internal interface UserService {
    fun findAll(): List<User>
    fun getUserById(id: Int): User
}