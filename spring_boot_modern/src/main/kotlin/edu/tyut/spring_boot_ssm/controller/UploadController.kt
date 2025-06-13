package edu.tyut.spring_boot_ssm.controller

import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.HandlerFilterFunction
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@RestController
private final class UploadController {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = ["/upload"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    private final suspend fun upload(
        @RequestPart(value = "file")
        file: FilePart,
    ): Result<Boolean> = withContext(Dispatchers.IO) {
        val targetPath: Path = Files.createDirectories(Paths.get(Constants.UPLOAD_DIR))
        val targetFile: Path = targetPath.resolve(file.filename())
        logger.info("thread: ${Thread.currentThread()}, fileName: ${file.filename()}, targetPath: $targetPath, targetFile: $targetFile")
        return@withContext try {
            file.transferTo(targetFile).awaitSingleOrNull()
            Result.success(message = "success", data = true)
        } catch (e: Exception) {
            logger.error("upload", e)
            Result.failure(message = "failure", data = false)
        }
    }
}