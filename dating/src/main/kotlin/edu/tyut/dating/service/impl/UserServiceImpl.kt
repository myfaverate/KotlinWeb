package edu.tyut.dating.service.impl

import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.dao.UserDao
import edu.tyut.dating.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class UserServiceImpl (
    private val userDao: UserDao
) : UserService {
    override fun findAll(): List<User> {
        return userDao.findAll()
    }

    override fun getUserById(id: Int): User {
        return userDao.getUserById(id = id)
    }
}