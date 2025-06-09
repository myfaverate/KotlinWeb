package edu.tyut.dao.impl

import edu.tyut.bean.User
import edu.tyut.dao.UserDao
import edu.tyut.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.springframework.stereotype.Repository

@Repository
internal class UserDaoImpl internal constructor(): UserDao {
    override suspend fun findUserById(id: Int): User {
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
        return user
    }
    override suspend fun insetUser(user: User): Boolean {
        val id: Int = UserEntity.insert { updateBuilder: UpdateBuilder<*> ->
            updateBuilder[UserEntity.account] = user.account
            updateBuilder[UserEntity.password] = user.password
            updateBuilder[UserEntity.nickname] = user.nickname
        }.getOrNull(column = UserEntity.id)?.value ?: 0
        return id > 0
    }
}