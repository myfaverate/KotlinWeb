package edu.tyut.aspect.controller

import edu.tyut.aspect.service.BookService
import org.springframework.stereotype.Controller

@Controller
class BookController(
    private val bookService: BookService
){
    fun byBook(userId: Int, bookId: Int) {
        bookService.byBook(userId, bookId)
    }
}