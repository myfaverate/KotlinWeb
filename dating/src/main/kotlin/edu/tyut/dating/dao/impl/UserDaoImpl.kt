package edu.tyut.dating.dao.impl

import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.bean.exposed.UserEntity
import edu.tyut.dating.bean.exposed.UserEntity.account
import edu.tyut.dating.bean.exposed.UserEntity.id
import edu.tyut.dating.bean.exposed.UserEntity.nickname
import edu.tyut.dating.bean.exposed.UserEntity.password
import edu.tyut.dating.dao.UserDao
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.springframework.stereotype.Repository

@Repository
internal class UserDaoImpl : UserDao {
    override fun findAll(): List<User> {
        return UserEntity.selectAll().map { resultRow: ResultRow ->
            User(
                id = resultRow[id].value,
                account = resultRow[account],
                password = resultRow[password],
                nickname = resultRow[nickname],
            )
        }
    }

    override fun getUserById(id: Int): User {
        val user: User = UserEntity.selectAll().where { UserEntity.id eq id }.firstOrNull() ?.let { resultRow: ResultRow ->
            User(
                id = resultRow[UserEntity.id].value,
                account = resultRow[account],
                password = resultRow[password],
                nickname = resultRow[nickname],
            )
        } ?: User (
            id = 0,
            account = "default",
            password = "default",
            nickname = "default"
        )
        return user
    }
}