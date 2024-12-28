package edu.tyut.edu.tyut.controller

import edu.tyut.edu.tyut.bean.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserController{
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/hello")
    fun getHello(): String {
        logger.info("UserController get hello...")
        return "index"
    }

    @PostMapping("/user")
    @ResponseBody
    fun getUser(user: User): User {
        logger.info("UserController get user -> user: $user")
        return user
    }

    /**
     * Invoke-WebRequest -Uri "http://localhost:8080/Spring_MVC_02/user" -Method POST  -ContentType "application/x-www-form-urlencoded"  -Body @{userName="张书豪"; passWord="123456"}
     * Invoke-WebRequest -Uri "http://localhost:8080/Spring_MVC_02/jsonUser" -Headers @{ "Accept-Encoding" = "UTF-8" } -Method POST -ContentType "application/json; charset=UTF-8" -Body ($( @{username="搜索"; password="123456"} | ConvertTo-Json -Depth 1 ))
     */
    @PostMapping("/jsonUser")
    @ResponseBody
    fun getJsonUser(@RequestBody user: User): User {
        logger.info("UserController getJsonUser -> user: $user")
        return user
    }
}