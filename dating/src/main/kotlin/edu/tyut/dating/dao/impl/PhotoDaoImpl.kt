package edu.tyut.dating.dao.impl

import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.bean.exposed.PhotoEntity
import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.bean.exposed.UserEntity
import edu.tyut.dating.bean.exposed.UserEntity.account
import edu.tyut.dating.bean.exposed.UserEntity.id
import edu.tyut.dating.bean.exposed.UserEntity.nickname
import edu.tyut.dating.bean.exposed.UserEntity.password
import edu.tyut.dating.dao.PhotoDao
import edu.tyut.dating.dao.UserDao
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.statements.UpdateBuilder
import org.jetbrains.exposed.v1.core.statements.UpdateStatement
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
internal class PhotoDaoImpl : PhotoDao {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun insert(photo: Photo) {
        val photoId: EntityID<Long> = PhotoEntity.insert { updateBuilder: UpdateBuilder<*> ->
            updateBuilder[PhotoEntity.photoName] = photo.photoName
            updateBuilder[PhotoEntity.photoUrl] = photo.photoUrl
            updateBuilder[PhotoEntity.description] = photo.description
        } get PhotoEntity.id
        logger.info("Inserted photo: $photoId")
    }

    override fun update(photo: Photo) {
        val rows: Int = PhotoEntity.update(where = { PhotoEntity.id eq photo.id }) { updateStatement: UpdateStatement ->
            updateStatement[PhotoEntity.photoName] = photo.photoName
            updateStatement[PhotoEntity.photoUrl] = photo.photoUrl
            updateStatement[PhotoEntity.description] = photo.description
        }
        logger.info("Updated photo: $rows")
    }

    override fun deleteById(id: Long): Int {
        val rows: Int = PhotoEntity.deleteWhere { PhotoEntity.id eq id }
        logger.info("Deleted photo: $rows")
        return rows
    }

    override fun findAll(): List<Photo> {
        return PhotoEntity.selectAll().map { resultRow: ResultRow ->
            Photo(
                id = resultRow[PhotoEntity.id].value,
                photoName = resultRow[PhotoEntity.photoName],
                photoUrl = resultRow[PhotoEntity.photoUrl],
                description = resultRow[PhotoEntity.description],
            )
        }
    }

    override fun getPhotosPage(
        pageIndex: Int,
        pageSize: Int
    ): List<Photo> {
        return PhotoEntity.selectAll()
            .limit(count = pageSize)
            .offset(start = (pageIndex - 1) * pageSize.toLong()).map { resultRow: ResultRow ->
                Photo(
                    id = resultRow[PhotoEntity.id].value,
                    photoName = resultRow[PhotoEntity.photoName],
                    photoUrl = resultRow[PhotoEntity.photoUrl],
                    description = resultRow[PhotoEntity.description],
                )
            }
    }
}