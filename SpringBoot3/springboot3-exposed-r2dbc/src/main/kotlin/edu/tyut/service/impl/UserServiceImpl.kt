package edu.tyut.service.impl

import edu.tyut.bean.User
import edu.tyut.dao.UserDao
import edu.tyut.service.UserService
import kotlinx.coroutines.Deferred
import org.springframework.stereotype.Service

@Service
internal class UserServiceImpl internal constructor(
    private val userDao: UserDao
) : UserService {
    override suspend fun findUserById(id: Int): Deferred<User> {
        return userDao.findUserById(id = id)
    }
}