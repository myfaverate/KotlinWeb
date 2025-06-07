package edu.tyut.spring_boot_ssm.dao.impl

import edu.tyut.spring_boot_ssm.dao.UserDao
import edu.tyut.spring_boot_ssm.entity.UserEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
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
            updateBuilder[UserEntity.createTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
            updateBuilder[UserEntity.updateTime] = Clock.System.now().toLocalDateTime(
                timeZone = TimeZone.of(zoneId = "Asia/Shanghai")
            )
        }.getOrNull(column = UserEntity.id)?.value ?: 0U
        return id > 0U
    }
}