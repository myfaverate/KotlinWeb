package edu.tyut.service.impl

import edu.tyut.bean.User
import edu.tyut.dao.UserDao
import edu.tyut.service.UserService
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.springframework.stereotype.Service

@Service
internal final class UserServiceImpl internal constructor(
    private val userDao: UserDao
) : UserService {

    override suspend fun findUserById(id: Int): Deferred<User> = suspendTransactionAsync {
        return@suspendTransactionAsync userDao.findUserById(id = id)
    }

    override suspend fun insetUser(user: User): Deferred<Boolean> = suspendTransactionAsync {
        userDao.insetUser(user = user)
    }
}