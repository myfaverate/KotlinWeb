package edu.tyut.dating.controller

import edu.tyut.dating.bean.Result
import edu.tyut.dating.bean.exposed.Photo
import edu.tyut.dating.service.PhotoService
import edu.tyut.dating.utils.Constants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.InputStream
import java.io.OutputStream
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
        logger.info("getPhotos -> headers: $headers")
        return photoService.findAll().map { it.copy(photoUrl = "${Constants.BASE_URL}${it.photoUrl}") }
    }

    @GetMapping(value = ["/getPhotosPage"], produces = [MediaType.APPLICATION_JSON_VALUE])
    internal suspend fun getPhotosPage(
        @RequestParam(value = "pageIndex", defaultValue = "0") pageIndex: Int,
        @RequestParam(value = "pageSize", defaultValue = "20") pageSize: Int,
        @RequestHeader headers: Map<String, String>,
    ): List<Photo> {
        logger.info("getPhotos -> pageIndex: ${pageIndex}, pageSize: ${pageSize}, headers: $headers")
        return photoService.getPhotosPage(pageIndex = pageIndex, pageSize = pageSize)
            .map { it.copy(photoUrl = "${Constants.BASE_URL}${it.photoUrl}") }
    }

    @PostMapping(value = ["/insert"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    internal suspend fun insert(
        @RequestParam(value = "photoName")
        photoName: String,
        @RequestParam(value = "description")
        description: String,
        @RequestParam(value = "photo")
        photo: MultipartFile,
        @RequestHeader
        headers: Map<String, String>,
    ): Result<Boolean> {
        val uploadFile: File = Paths.get(Constants.UPLOAD_DIR, photo.originalFilename).toFile()
        logger.info("insert -> photoName: $photoName, description: $description, headers: $headers, photoName: ${photo.originalFilename}, photoSize: ${photo.size}, uploadPath: $uploadFile, toURI: ${uploadFile.toURI().path}")
        return try {

            if (!uploadFile.parentFile.exists()) {
                val isSuccess: Boolean = uploadFile.parentFile.mkdirs()
                logger.info("insert -> 创建是否成功: $isSuccess")
            }

            photo.inputStream.use { input: InputStream ->
                uploadFile.outputStream().use { output: OutputStream ->
                    input.copyTo(out = output, bufferSize = 1024)
                }
            }
            photoService.insert(
                photo = Photo(
                    id = 0,
                    description = description,
                    photoName = photoName,
                    photoUrl = uploadFile.name
                ).apply {
                    logger.info("insert -> Photo: $this")
                }
            )
            Result.success(message = "success", data = true)
        } catch (e: Exception){
            logger.error(e.message, e)
            Result.failure(message = "failure", data = false)
        }
    }

    @DeleteMapping(value = ["/delete"])
    internal fun deleteById(
        @RequestBody photo: Photo,
        @RequestHeader headers: Map<String, String>,
    ): Result<Boolean> {
        logger.info("deleteById -> photo: $photo, headers: $headers")
        val fileName: String = photo.photoUrl.substringAfterLast("/")
        val photoFile: File = Paths.get(Constants.UPLOAD_DIR, fileName).toFile()
        if (photoFile.exists()) {
            val isSuccess: Boolean = photoFile.delete()
            logger.info("deleteById -> fileName: $fileName, photoFile: $photoFile, 删除是否 isSuccess: $isSuccess")
        }
        return if (photoService.deleteById(id = photo.id) > 0) Result.success(message = "success", data = true) else Result.failure(message = "failure", data = false)
    }

    @PutMapping(value = ["/update"])
    internal suspend fun update(
        @RequestPart(value = "photo")
        photo: Photo,
        @RequestPart(value = "photoFile")
        photoFile: MultipartFile,
        @RequestHeader headers: Map<String, String>,
    ): Result<Boolean> {
        val uploadFile: File = Paths.get(Constants.UPLOAD_DIR, photoFile.originalFilename).toFile()
        logger.info("update -> photo: $photo, headers: $headers, photoFileName: ${photoFile.originalFilename}, photoFileSize: ${photoFile.size}, uploadFile: $uploadFile, toURI: ${uploadFile.toURI().path}")
        return try {

            if (!uploadFile.parentFile.exists()) {
                val isSuccess: Boolean = uploadFile.parentFile.mkdirs()
                logger.info("update -> 创建是否成功: $isSuccess")
            }

            photoFile.inputStream.use { input: InputStream ->
                uploadFile.outputStream().use { output: OutputStream ->
                    input.copyTo(out = output, bufferSize = 1024)
                }
            }

            val fileName: String = photo.photoUrl.substringAfterLast("/")
            val photoFile: File = Paths.get(Constants.UPLOAD_DIR, fileName).toFile()
            if (photoFile.exists()) {
                val isSuccess: Boolean = photoFile.delete()
                logger.info("update -> fileName: $fileName, photoFile: $photoFile, isSuccess: $isSuccess")
            }

            photoService.update(
                photo = photo.copy(photoUrl = uploadFile.name),
            )
            Result.success(message = "success", data = true)
        } catch (e: Exception){
            logger.error(e.message, e)
            Result.failure(message = "failure", data = false)
        }
    }
}