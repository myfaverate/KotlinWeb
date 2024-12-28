package edu.tyut.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory

@WebServlet(name = "HelloController", value = ["/hello"])
class HelloController : HttpServlet() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        logger.info("GET /hello")
        response?.writer?.write("Hello1 World!")
    }
    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}