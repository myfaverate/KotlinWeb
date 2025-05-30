package edu.tyut.dating.controller

import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.dao.PhotoDao
import edu.tyut.dating.service.PhotoService
import edu.tyut.dating.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "PhotoController")
@RequestMapping(value = ["/photo"])
internal class PhotoController(
    private val photoService: PhotoService
) {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/getPhotos"], produces = [MediaType.APPLICATION_JSON_VALUE])
    internal fun getPhotos(
        @RequestHeader headers: Map<String, String>,
    ): List<Photo> {
        logger.info("getPhotos -> headers: $headers")
        return photoService.findAll()
    }

    @PostMapping(value = ["/insert"])
    internal fun insert(
        @RequestBody photo: Photo,
        @RequestHeader headers: Map<String, String>,
    ) {
        logger.info("insert -> photo: $photo, headers: $headers")
        return photoService.insert(photo = photo)
    }

    @DeleteMapping(value = ["/delete/{id}"])
    internal fun deleteById(
        @PathVariable(value = "id") id: Long,
        @RequestHeader headers: Map<String, String>,
    ) {
        logger.info("deleteById -> id: $id, headers: $headers")
        return photoService.deleteById(id)
    }

    @PutMapping(value = ["/update"])
    internal fun update(
        @RequestBody photo: Photo,
        @RequestHeader headers: Map<String, String>,
    ) {
        logger.info("update -> photo: $photo, headers: $headers")
        return photoService.update(photo = photo)
    }
}