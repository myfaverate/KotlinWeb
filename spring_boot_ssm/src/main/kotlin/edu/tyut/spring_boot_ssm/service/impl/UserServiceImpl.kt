package edu.tyut.spring_boot_ssm.service.impl

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
}