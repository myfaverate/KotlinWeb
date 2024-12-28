package edu.tyut.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.fileupload2.core.DiskFileItem
import org.apache.commons.fileupload2.core.DiskFileItemFactory
import org.apache.commons.fileupload2.core.FileItemInput
import org.apache.commons.fileupload2.core.FileItemInputIterator
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path

@WebServlet(name = "FileController", value = ["/file"])
class FileController : HttpServlet() {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request == null || response == null) {
            logger.error("request == null or response == null")
            return
        }
        if(JakartaServletFileUpload.isMultipartContent(request)){
            val diskFileItemFactory: DiskFileItemFactory = DiskFileItemFactory.builder().setCharset(Charsets.UTF_8).get()
            logger.info("diskFileItemFactory -> threshold: ${diskFileItemFactory.threshold}, repository: ${diskFileItemFactory.repository}, charsetDefault: ${diskFileItemFactory.charsetDefault}, fileCleaningTracker: ${diskFileItemFactory.fileCleaningTracker}")
            val fileUpload: JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> = JakartaServletFileUpload(diskFileItemFactory)
            logger.info("fileUpload -> sizeMax: ${fileUpload.sizeMax}, fileCountMax: ${fileUpload.fileCountMax}, headerCharset: ${fileUpload.headerCharset}")
            // val fileItemInputIterator: FileItemInputIterator = fileUpload.getItemIterator(request)
            // for(fileItem: FileItemInput in fileItemInputIterator.asIterator()) {
            //     logger.info("fileItem -> name: ${fileItem.name}, headers: ${fileItem.headers.headerNames}, contentType: ${fileItem.contentType}, fieldName: ${fileItem.fieldName}")
            // }
            val formItems: List<DiskFileItem> = fileUpload.parseRequest(request)
            for(fileItem: DiskFileItem in formItems) {
                if (fileItem.isFormField){
                    // 正常读取
                    val fieldName: String = fileItem.fieldName
                    val fieldValue: String = fileItem.getString(Charsets.UTF_8)
                    logger.info("isFormField fileItem -> fieldName: $fieldName, fieldValue: $fieldValue")
                }else{
                    val fieldName: String = fileItem.fieldName
                    val fieldValue: String = fileItem.name
                    logger.info("notFormField fileItem -> fieldName: $fieldName, fieldValue: $fieldValue")
                    if (fileItem.name.isNullOrEmpty() || fileItem.size <= 0){
                        response.writer?.println("file is null or empty")
                        return
                    }
                    val uploadFile = File(request.servletContext.getRealPath("upload"))
                    if (!uploadFile.exists()){
                        val isSuccess: Boolean = uploadFile.mkdirs()
                        logger.info("fileItem -> 文件创建是否成功: $isSuccess")
                    }
                    fileItem.write(Path(uploadFile.absolutePath, fileItem.name).apply {
                        logger.info("uploadFilePath: $this")
                    })
                }
            }
        }
        response.writer?.println("Success...")
    }
    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}