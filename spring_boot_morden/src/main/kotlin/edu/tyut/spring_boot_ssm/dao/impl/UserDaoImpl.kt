package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.bean.User
import edu.tyut.spring_boot_ssm.dao.UserDao
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.core.statements.UpdateStatement
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.update
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository // not allow final
internal class UserDaoImpl internal constructor(): UserDao {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override suspend fun isUserExists(username: String): Boolean {
        logger.info("isUserExists exists $username")
        val result: Boolean = UserEntity.select(column = UserEntity.username).where {
            UserEntity.username eq username
        }.empty().not()
        logger.info("isUserExists result $result")
        return result
    }

    override suspend fun addUser(
        username: String,
        password: String
    ): Boolean {
        val id: UInt = UserEntity.insert { updateBuilder: UpdateBuilder<*> ->
            updateBuilder[UserEntity.username] = username
            updateBuilder[UserEntity.password] = password
            updateBuilder[UserEntity.nickname] = "unknown"
            updateBuilder[UserEntity.userPicture] = ""
            updateBuilder[UserEntity.email] = ""
            updateBuilder[UserEntity.createTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
            updateBuilder[UserEntity.updateTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
        }.getOrNull(column = UserEntity.id)?.value ?: 0U
        return id > 0U
    }

    override suspend fun findUserByUsername(username: String): User {
        val result: User = UserEntity.selectAll().where {
            UserEntity.username eq username
        }.limit(count = 1).firstOrNull()?.let { resultRow: ResultRow ->
            User(
                id = resultRow[UserEntity.id].value,
                username = resultRow[UserEntity.username],
                password = resultRow[UserEntity.password],
                nickname = resultRow[UserEntity.nickname],
                email = resultRow[UserEntity.email],
                createTime = resultRow[UserEntity.createTime],
                updateTime = resultRow[UserEntity.updateTime],
                userPicture = resultRow[UserEntity.userPicture],
            )
        } ?: User(
            id = 0U,
            username = "unknown",
            password = "unknown",
            nickname = "unknown",
            userPicture = "unknown",
            email = "unknown",
            createTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai")),
            updateTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
        )
        logger.info("userDao user: $result, thread: ${Thread.currentThread()}")
        return result
    }

    override suspend fun updateUser(user: User): Boolean {
        val rows: Int = UserEntity.update(where = { UserEntity.id eq user.id }) { updateStatement: UpdateStatement ->
            updateStatement[UserEntity.nickname] = user.nickname
            updateStatement[UserEntity.email] = user.email
            updateStatement[UserEntity.updateTime] = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
        }
        return rows > 0
    }

    override suspend fun updateAvatar(id: UInt, avatarUrl: String): Boolean {
        val rows: Int = UserEntity.update(where = { UserEntity.id eq id }) { updateStatement: UpdateStatement ->
            updateStatement[UserEntity.userPicture] = avatarUrl
            updateStatement[UserEntity.updateTime] = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
        }
        return rows > 0
    }

    override suspend fun updatePassword(id: UInt, password: String): Boolean {
        val rows: Int = UserEntity.update(where = { UserEntity.id eq id }) { updateStatement: UpdateStatement ->
            updateStatement[UserEntity.password] = password
            updateStatement[UserEntity.updateTime] = Clock.System.now().toLocalDateTime(timeZone = TimeZone.of(zoneId = "Asia/Shanghai"))
        }
        return rows > 0
    }
}