package edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.PrintWriter

/**
 * https://www.bilibili.com/video/BV1UN411x7xe?spm_id_from=333.788.player.switch&vd_source=fb5fc0881b2bb1a411566e5b2f1c7c7e&p=68
 */

@WebServlet(urlPatterns = ["/user"], name = "userServlet", asyncSupported = true)
internal class UserServlet : CoroutineServlet() {

    private val logger: Logger = LoggerFactory.getLogger(UserServlet::class.java)

    override suspend fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val username: String? = request.getParameter("username")
        logger.info("username: $username")
        response.writer?.use { writer: PrintWriter ->
            writer.write("Hello, $username!")
        }
    }
}