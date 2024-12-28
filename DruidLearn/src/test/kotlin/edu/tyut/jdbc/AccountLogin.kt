package edu.tyut.jdbc

import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.pool.DruidDataSourceFactory
import com.mysql.cj.jdbc.Driver
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement
import java.util.*
import javax.sql.DataSource
import kotlin.test.Test


/**
 * 1、尚硅谷 jdbc 第18集
 * 2、尚硅谷 javaWeb 第96集
 * 3、尚硅谷2024最新JDBC教程
 */
class AccountLogin {
    @Test
    fun login(){

        val properties = Properties()
        javaClass.getResourceAsStream("/jdbc.properties")?.use {
            properties.load(it)
        }
        Class.forName(properties.getProperty("driver"))
        val connection: Connection = DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("username"),
            properties.getProperty("password")
        )
        val statement: Statement = connection.createStatement()

        val resultSet: ResultSet = statement.executeQuery("SELECT * FROM t_user")
        val metaData: ResultSetMetaData = resultSet.metaData
        println(metaData.columnCount)

        while (resultSet.next()){
            val id: Int = resultSet.getInt("id")
            val account: String = resultSet.getString("account")
            val password: String = resultSet.getString("password")
            val nickname: String = resultSet.getString("nickname")
            println("id: $id, account: $account, password: $password, nickname: $nickname")
        }
        // 6. 关闭资源
        resultSet.close()
        statement.close()
        connection.close()
    }
    @Test
    fun insert(){

        val properties = Properties()
        javaClass.getResourceAsStream("/jdbc.properties")?.use {
            properties.load(it)
        }
        Class.forName(properties.getProperty("driver"))
        val connection: Connection = DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("username"),
            properties.getProperty("password")
        )

        val sql = "insert into t_user(account, password, nickname) values (?,?,?)"
        val statement: PreparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, "ITGuoKe")
        statement.setString(2, "zsh123..")
        statement.setString(3, "ITGuoKe")

        val column: Int = statement.executeUpdate()

        if (column > 0) {
            println("插入成功!")
            // 回显主键
            statement.generatedKeys.use { resultSet: ResultSet ->
                resultSet.next()
                val id = resultSet.getInt(1)
                println("key: $id")
            }
        }else{
            println("插入失败!")
        }



        // 6. 关闭资源
        statement.close()
        connection.close()
    }
    @Test
    fun insertBatch(){

        val properties = Properties()
        javaClass.getResourceAsStream("/jdbc.properties")?.use {
            properties.load(it)
        }
        Class.forName(properties.getProperty("driverClassName"))
        val connection: Connection = DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("username"),
            properties.getProperty("password")
        )

        val sql = "insert into t_user(account, password, nickname) values (?,?,?)"
        val statement: PreparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)

        for(i in 1 until 5) {
            statement.setString(1, "ITGuoKed->$i")
            statement.setString(2, "zsh123d..->$i")
            statement.setString(3, "ITGuoKed->$i")
            statement.addBatch()
        }
        val columns: IntArray = statement.executeBatch()
        println("columns: ${columns.joinToString(prefix = "[", postfix = "]")}")

        columns.forEach {
            if (it <= 0){
                println("插入第${it}行失败!")
                return@forEach
            }
            println("插入成功!")
            // 回显主键
            statement.generatedKeys.use { resultSet: ResultSet ->
                while(resultSet.next()) {
                    val id = resultSet.getInt(1)
                    println("key: $id")
                }
            }
        }

        // 6. 关闭资源
        statement.close()
        connection.close()
    }

    @Test
    fun druid(){

        val properties = Properties()
        javaClass.getResourceAsStream("/jdbc.properties")?.use {
            properties.load(it)
        }

        val dataSource: DataSource = DruidDataSourceFactory.createDataSource(properties)
        val connection: Connection = dataSource.connection

        val sql = "insert into t_user(account, password, nickname) values (?,?,?)"
        val statement: PreparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)


            statement.setString(1, "ITGuoKed->$")
            statement.setString(2, "zsh123d..->$")
            statement.setString(3, "ITGuoKed->$")
            statement.addBatch()

        val columns: IntArray = statement.executeBatch()
        println("columns: ${columns.joinToString(prefix = "[", postfix = "]")}")

        columns.forEach {
            if (it <= 0){
                println("插入第${it}行失败!")
                return@forEach
            }
            println("插入成功!")
            // 回显主键
            statement.generatedKeys.use { resultSet: ResultSet ->
                while(resultSet.next()) {
                    val id = resultSet.getInt(1)
                    println("key: $id")
                }
            }
        }

        // 6. 关闭资源
        statement.close()
        connection.close()
    }

}