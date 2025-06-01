package edu.tyut.dating.dao

import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.bean.exposed.User

internal interface PhotoDao {
    fun insert(photo: Photo)
    fun update(photo: Photo)
    fun deleteById(id: Long): Int
    fun findAll(): List<Photo>
    fun getPhotosPage(pageIndex: Int, pageSize: Int): List<Photo>
}