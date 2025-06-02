package edu.tyut.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
internal class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)
    @RequestMapping(value = ["/hello"], produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseBody
    private suspend fun hello(): String = withContext(Dispatchers.Default) {
        "Hello World"
    }
}