package edu.tyut.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @GetMapping("/hello")
    @ResponseBody
    fun hello(): String {
        logger.info("UserController hello1...")
        if (1 == 1){
            throw RuntimeException("UserController hello1223455112...搜索")
        }
        return "Hello 世界！"
    }
}