package edu.tyut.aspect.controller

import edu.tyut.aspect.service.UserService
import org.springframework.stereotype.Controller

@Controller
class UserController(
    private val userService: UserService
){
    fun saveUser(){
        userService.saveUser()
    }
}