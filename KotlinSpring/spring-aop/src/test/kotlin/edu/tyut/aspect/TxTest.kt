package edu.tyut.aspect

import com.alibaba.druid.filter.AutoLoad
import edu.tyut.aspect.controller.BookController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@SpringJUnitConfig
@ContextConfiguration(locations = ["classpath:applicationContext.xml"])
class TxTest(
    @Autowired
    private val bookController: BookController,
    @Autowired
    private val jdbcTemplate: JdbcTemplate,
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @BeforeTest
    @AfterTest
    fun beforeTest(){
        val books = jdbcTemplate.queryForList("select * from t_book")
        val users = jdbcTemplate.queryForList("select * from t_user")
        logger.info("books: $books, users: $users")
    }

    /**
     * 114
     */
    @Test
    fun byBookTest(){
        bookController.byBook(1, 1)
    }
}