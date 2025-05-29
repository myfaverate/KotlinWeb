package edu.tyut.spring_boot_security.controllor

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WriteController {
    @PreAuthorize(value = "hasAuthority('write')")
    @GetMapping(value = ["/write"])
    fun write(): String {
        return "write 成功！"
    }
}