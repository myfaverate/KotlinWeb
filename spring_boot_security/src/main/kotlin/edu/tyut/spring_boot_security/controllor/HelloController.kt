package edu.tyut.spring_boot_security.controllor

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {
    @GetMapping(value = ["/hello"])
    fun hello(model: Model): String {
        model.addAttribute("message", "Hello World 世界！")
        return "hello"
    }
}