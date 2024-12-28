package edu.tyut.edu.tyut.dao.impl

import edu.tyut.edu.tyut.dao.BookDao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class BookDaoImpl : BookDao {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun getPriceByBookId(bookId: Int): Int {
        return 0
    }

    override fun updateStock(bookId: Int) {
    }

    override fun updateBalance(userId: Int, price: Int) {
    }
}