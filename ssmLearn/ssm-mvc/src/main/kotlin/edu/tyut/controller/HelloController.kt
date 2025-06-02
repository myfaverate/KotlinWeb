package edu.tyut.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
private class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)
    @RequestMapping(value = ["/hello"], produces = [MediaType.TEXT_PLAIN_VALUE], method = [RequestMethod.GET])
    @ResponseBody
    private suspend fun hello(
        @RequestParam(value = "name") name: String,
    ): String = withContext(context = Dispatchers.Default) {
        logger.info("Starting hello name $name")
        "Hello World"
    }
}