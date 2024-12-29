package edu.tyut.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception, model: Model): String {
        logger.error("ExceptionController handleException ...")
        model.addAttribute("exception", exception)
        return "error"
    }
}