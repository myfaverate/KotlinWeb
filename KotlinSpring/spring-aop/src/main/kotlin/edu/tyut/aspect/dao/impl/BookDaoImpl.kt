package edu.tyut.aspect.dao.impl

import edu.tyut.aspect.dao.BookDao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
) : BookDao {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun getPriceByBookId(bookId: Int): Int {
        return jdbcTemplate.queryForObject("select price from t_book where book_id = ?", Int::class.java, bookId).apply {
            logger.debug("getPriceByBookId -> price: $this")
        }
    }

    override fun updateStock(bookId: Int) {
        jdbcTemplate.update("update t_book set stock = stock - 1 where book_id = ?", bookId)
    }

    override fun updateBalance(userId: Int, price: Int) {
        jdbcTemplate.update("update t_user set balance = balance - ? where user_id = ?", price, userId)
    }
}