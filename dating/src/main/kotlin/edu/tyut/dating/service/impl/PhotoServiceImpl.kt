package edu.tyut.dating.service.impl

import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.dao.PhotoDao
import edu.tyut.dating.service.PhotoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class PhotoServiceImpl (
    private val photoDao: PhotoDao,
) : PhotoService {

    override fun insert(photo: Photo) {
        photoDao.insert(photo)
    }

    override fun update(photo: Photo) {
        photoDao.update(photo)
    }

    override fun deleteById(id: Long): Int {
        return photoDao.deleteById(id)
    }

    override fun findAll(): List<Photo> {
        return photoDao.findAll()
    }

    override fun getPhotosPage(pageIndex: Int, pageSize: Int): List<Photo> {
        return photoDao.getPhotosPage(pageIndex = pageIndex, pageSize = pageSize)
    }

}