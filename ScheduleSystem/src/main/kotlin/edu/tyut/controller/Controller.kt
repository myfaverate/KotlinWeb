package edu.tyut.controller

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class Controller : HttpServlet() {
    protected val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    protected val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        val methodName: String = request?.requestURI?.substringAfterLast('/') ?: ""
        logger.info("Request methodName: $methodName")
        runCatching {
            javaClass.getDeclaredMethod(methodName, HttpServletRequest::class.java, HttpServletResponse::class.java)
                .apply {
                    isAccessible = true
                    response?.contentType = "application/json;charset=UTF-8"
                    invoke(this@Controller, request, response)
                }
        }.onFailure {
            logger.error(it.message, it)
        }
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }

    override fun destroy() {
        super.destroy()
        coroutineScope.cancel()
    }
}