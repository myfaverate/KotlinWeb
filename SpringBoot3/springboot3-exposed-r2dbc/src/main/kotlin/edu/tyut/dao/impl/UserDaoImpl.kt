package edu.tyut.dao.impl

import edu.tyut.bean.User
import edu.tyut.dao.UserDao
import edu.tyut.entity.UserEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.firstOrNull
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.springframework.stereotype.Repository

@Repository
internal class UserDaoImpl : UserDao {
    override suspend fun findUserById(id: Int): Deferred<User> = suspendTransactionAsync {
        val user: User = UserEntity.selectAll().where { UserEntity.id eq id }.firstOrNull() ?.let { resultRow: ResultRow ->
            User(
                id = resultRow[UserEntity.id].value,
                account = resultRow[UserEntity.account],
                password = resultRow[UserEntity.password],
                nickname = resultRow[UserEntity.nickname],
            )
        } ?: User (
            id = 0,
            account = "default",
            password = "default",
            nickname = "default"
        )
        return@suspendTransactionAsync  user
    }
}