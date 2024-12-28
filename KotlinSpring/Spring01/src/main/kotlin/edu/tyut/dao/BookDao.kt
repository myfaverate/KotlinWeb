package edu.tyut.edu.tyut.dao

interface BookDao {
    fun getPriceByBookId(bookId: Int): Int
    fun updateStock(bookId: Int)
    fun updateBalance(userId: Int, price: Int)
}