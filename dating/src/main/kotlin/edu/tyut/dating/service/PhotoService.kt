package edu.tyut.dating.service

import edu.tyut.dating.bean.exposed.Photo

internal interface PhotoService {
    fun insert(photo: Photo)
    fun update(photo: Photo)
    fun deleteById(id: Long)
    fun findAll(): List<Photo>
}