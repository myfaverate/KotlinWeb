package edu.tyut.springboot3_01.edu.tyut.controller

import edu.tyut.springboot3_01.edu.tyut.bean.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * /mnt/d/SoftWare/LanguageProjects/KotlinWebProjects/SpringBoot3_01/build/libs/SpringBoot3_01-0.0.1-SNAPSHOT.jar
 * p=21
 */
@Tag(name = "Hello World", description = "Hello World 测试")
@Controller
class HelloController(
    private val stringRedisTemplate: StringRedisTemplate,
    private val user: User
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Operation(summary = "Get Hello World", description = "Hello World  世界")
    @GetMapping("/hello")
    @ResponseBody
    fun hello(@Parameter(description = "姓名") name: String): String {
        logger.info("Hello...")
        return "Hello World 世界! name: $name"
    }

    @GetMapping("/increment")
    @ResponseBody
    fun increment(): String {
        return stringRedisTemplate.opsForValue().increment("count").toString()
    }

    @GetMapping("/user")
    @ResponseBody
    fun getUser(): User {
        return user
    }

}