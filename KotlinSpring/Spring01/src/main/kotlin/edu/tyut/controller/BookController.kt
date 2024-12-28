package edu.tyut.edu.tyut.controller

import edu.tyut.edu.tyut.service.BookService
import org.springframework.stereotype.Controller

@Controller
class BookController(
    private val bookService: BookService
){
    fun byBook(userId: Int, bookId: Int) {

    }
}