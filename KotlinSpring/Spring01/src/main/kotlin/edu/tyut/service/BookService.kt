package edu.tyut.edu.tyut.service

import java.awt.print.Book

interface BookService {
    fun byBook(userId: Int, bookId: Int)
}