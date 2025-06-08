package edu.tyut.spring_boot_ssm.service.impl

import edu.tyut.spring_boot_ssm.bean.User
import edu.tyut.spring_boot_ssm.dao.UserDao
import edu.tyut.spring_boot_ssm.extension.md5
import edu.tyut.spring_boot_ssm.service.UserService
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.springframework.stereotype.Service

@Service
internal final class UserServiceImpl internal constructor(
    private val userDao: UserDao,
) : UserService {

    override suspend fun isUserExists(username: String): Deferred<Boolean> = suspendTransactionAsync {
        return@suspendTransactionAsync userDao.isUserExists(username)
    }

    override suspend fun register(
        username: String,
        password: String
    ): Deferred<Boolean> = suspendTransactionAsync {
        return@suspendTransactionAsync userDao.addUser(username = username, password = password.md5())
    }

    override suspend fun findUserByUsername(username: String): Deferred<User> = suspendTransactionAsync {
        return@suspendTransactionAsync userDao.findUserByUsername(username)
    }

    override suspend fun updateUser(user: User): Deferred<Boolean> = suspendTransactionAsync {
        userDao.updateUser(user = user)
    }

    override suspend fun updateAvatar(id: UInt, avatarUrl: String): Deferred<Boolean> = suspendTransactionAsync {
        userDao.updateAvatar(id = id, avatarUrl = avatarUrl)
    }

    override suspend fun updatePassword(
        id: UInt,
        password: String
    ): Deferred<Boolean> = suspendTransactionAsync{
        userDao.updatePassword(id = id, password = password)
    }
}