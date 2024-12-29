package edu.tyut.springboot3_01.edu.tyut.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * p=21
 */
@Controller
class HelloController(
    private val stringRedisTemplate: StringRedisTemplate
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @GetMapping("/")
    @ResponseBody
    fun hello(): String {
        logger.info("Hello...")
        return "Hello World 世界!"
    }

    @GetMapping("/increment")
    @ResponseBody
    fun increment(): String {
        return stringRedisTemplate.opsForValue().increment("count").toString()
    }

}