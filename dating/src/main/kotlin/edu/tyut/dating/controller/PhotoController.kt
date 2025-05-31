package edu.tyut.dating.controller

import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.bean.exposed.User
import edu.tyut.dating.dao.PhotoDao
import edu.tyut.dating.service.PhotoService
import edu.tyut.dating.service.UserService
import edu.tyut.dating.utils.Constants
import jakarta.servlet.annotation.MultipartConfig
import kotlinx.coroutines.coroutineScope
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.nio.file.Paths

@RestController(value = "PhotoController")
@RequestMapping(value = ["/photo"])
internal class PhotoController(
    private val photoService: PhotoService
) {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/getPhotos"], produces = [MediaType.APPLICATION_JSON_VALUE])
    internal suspend fun getPhotos(
        @RequestHeader headers: Map<String, String>,
    ): List<Photo> {
        coroutineScope {  }
        logger.info("getPhotos -> headers: $headers")
        return photoService.findAll()
    }

    @PostMapping(value = ["/insert"])
    internal fun insert(
        @RequestParam(value = "photoName")
        photoName: String,
        @RequestParam(value = "description")
        description: String,
        @RequestParam(value = "photo")
        photo: MultipartFile,
        @RequestHeader
        headers: Map<String, String>,
    ): Boolean {
        val uploadPath: Path = Paths.get(Constants.UPLOAD_DIR, photo.originalFilename)
        logger.info("insert -> photoName: $photoName, description: $description, headers: $headers, photoName: ${photo.originalFilename}, photoSize: ${photo.size}, uploadPath: $uploadPath")
        return try {
            photo.transferTo(uploadPath)
            photoService.insert(
                photo = Photo(
                    id = 0,
                    description = description,
                    photoName = photoName,
                    photoUrl = uploadPath.toString()
                ).apply {
                    logger.info("insert -> Photo: $this")
                }
            )
            true
        }catch (e: Exception){
            logger.error(e.message, e)
            false
        }
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