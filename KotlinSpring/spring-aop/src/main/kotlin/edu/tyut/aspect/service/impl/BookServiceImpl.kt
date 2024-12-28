package edu.tyut.aspect.service.impl

import edu.tyut.aspect.dao.BookDao
import edu.tyut.aspect.service.BookService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class BookServiceImpl(
    private val bookDao: BookDao,
) : BookService {
    @Transactional
    override fun byBook(userId: Int, bookId: Int) {
        val price: Int = bookDao.getPriceByBookId(bookId)
        bookDao.updateStock(bookId)
        bookDao.updateBalance(userId, price)
    }
}