package edu.tyut.edu.tyut.controller

import edu.tyut.edu.tyut.service.UserService
import org.springframework.stereotype.Controller

@Controller
class UserController(
    private val userService: UserService
){
    fun saveUser(){
        userService.saveUser()
    }
}