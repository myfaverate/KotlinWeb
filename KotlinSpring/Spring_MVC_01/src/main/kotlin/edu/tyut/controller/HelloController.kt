package edu.tyut.controller

import edu.tyut.bean.User
import jakarta.servlet.ServletContext
import org.slf4j.LoggerFactory
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.MimeType
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream

@Controller
class HelloController(
    private val servletContext: ServletContext
){

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/hello")
    fun hello(): String {
        return "index"
    }
    @GetMapping("/getUser")
    @ResponseBody
    fun getUser(@RequestBody user: User): String {

        logger.info("UserController getUser: $user")
        return "Hello World 世界！"
    }
    @GetMapping("/sendUser")
    @ResponseBody
    fun sendUser(): User {
        val user = User("张书豪", "123456")
        return user
    }
    @GetMapping("/downloadImage")
    fun downloadImage(): ResponseEntity<ByteArray> {
        val imagePathFile = File(servletContext.getRealPath("/img/kotlin.webp"))
        logger.info("downloadImage servletContext: $servletContext, imagePathFile: $imagePathFile")
        if (imagePathFile.exists()) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imagePathFile.readBytes())
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
    @PostMapping("/upload")
    @ResponseBody
    fun upload(@RequestParam(value = "upload_file") uploadFile: MultipartFile): String {
        logger.info("originalFilename: ${uploadFile.originalFilename}, name: ${uploadFile.name}")
        val tmpPath: String = servletContext.getAttribute("jakarta.servlet.context.tempdir").toString()
        logger.info("tmpPath: $tmpPath")
        uploadFile.transferTo(File("${tmpPath}${File.separator}${uploadFile.originalFilename}").apply {
            logger.info("uploadFilePath: $this")
        })
        return "success"
    }
}