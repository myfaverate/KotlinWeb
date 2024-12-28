package edu.tyut.aspect

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.sql.Connection
import kotlin.test.Test

@SpringJUnitConfig
@ContextConfiguration(locations = ["classpath:applicationContext.xml"])
class JdbcTest(
    @Autowired
    private val jdbcTemplate: JdbcTemplate,
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun jdbcTest(){
        logger.info("jdbcTemplate: $jdbcTemplate")
        logger.info("jdbcTemplate: $jdbcTemplate")
        val users: List<Map<String, Any>> = jdbcTemplate.queryForList("SELECT * FROM t_user")
        logger.info("users: $users")
        // 插入数据
        // id=2, username=zsh, password=zsh, age=18, gender=男, email=zsh@zsh.com
        val rows: Int = jdbcTemplate.update("insert into t_user values(null, ?, ?, ?, ?, ?)", "小美", "123456", 14, "男", "123456@qq.com")
        if (rows > 0) {
            logger.info("插入成功: $rows")
        }else{
            logger.info("插入失败: $rows")
        }
    }

    /**
     * SSM 112集
     */
    @Suppress("SqlNoDataSourceInspection")
    @Test
    fun selectById(){
        val sql = """
            DROP TABLE IF EXISTS t_user;
            DROP TABLE IF EXISTS t_book;
            
            CREATE TABLE IF NOT EXISTS `t_book` (
                `book_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                `book_name` VARCHAR(20) DEFAULT NULL COMMENT '图书名称',
                `price` INT DEFAULT NULL COMMENT '图书价格',
                `stock` INT UNSIGNED DEFAULT NULL COMMENT '库存（无符号）'
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
            
            INSERT INTO t_book (book_id, book_name, price, stock)
            VALUES (NULL, '斗破苍穹', 80, 100), 
                   (NULL, '斗罗大陆', 50, 100);

            CREATE TABLE IF NOT EXISTS `t_user` (
                `user_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                `username` VARCHAR(20) DEFAULT NULL COMMENT '用户名',
                `balance` INT UNSIGNED DEFAULT NULL COMMENT '余额（无符号）'
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

            INSERT INTO t_user (user_id, username, balance)
            VALUES (NULL, 'admin', 50);
        """.trimIndent()
        jdbcTemplate.dataSource?.connection?.use { connection: Connection ->
            ScriptUtils.executeSqlScript(connection, ByteArrayResource(sql.toByteArray(charset = Charsets.UTF_8)))
        }
        val tables = jdbcTemplate.queryForList("show tables")
        logger.info("tables: $tables")
        val books = jdbcTemplate.queryForList("select * from t_book")
        logger.info("books: $books")
        val users = jdbcTemplate.queryForList("select * from t_user")
        logger.info("users: $users")
    }
}