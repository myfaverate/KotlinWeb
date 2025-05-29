package edu.tyut.spring_boot_security.controllor

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping(value = ["/login"])
    fun write(): String {
        return "login"
    }
}