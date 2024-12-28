package edu.tyut

import com.alibaba.druid.pool.DruidDataSourceFactory
import java.lang.reflect.Field
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.util.*
import javax.sql.DataSource

open class BaseDao1 {
    fun executeUpdate(sql: String, vararg params: Any): Int {
        val connection: Connection = JDBCUtil1.connection
        val prepareStatement: PreparedStatement = connection.prepareStatement(sql)
        for(i in 1 .. params.size){
            prepareStatement.setObject(i, params[i - 1])
        }
        val rows: Int = prepareStatement.executeUpdate()
        prepareStatement.close()
        if (connection.autoCommit){
            JDBCUtil1.freeConnection()
        }
        return rows
    }
    fun <T> executeQuery(clazz: Class<T>, sql: String, vararg params: Any): List<T> {
        val connection: Connection = JDBCUtil1.connection
        val prepareStatement: PreparedStatement = connection.prepareStatement(sql)
        for(i in 1 .. params.size){
            prepareStatement.setObject(i, params[i - 1])
        }
        val resultSet: ResultSet = prepareStatement.executeQuery()
        val metaData: ResultSetMetaData = resultSet.metaData
        val columnCount: Int = resultSet.metaData.columnCount
        val list: MutableList<T> = mutableListOf()
        while (resultSet.next()){
            clazz.getDeclaredConstructor().isAccessible = true
            val data: T = clazz.getDeclaredConstructor().newInstance()
            for(i in 1..columnCount){
                val value: Any = resultSet.getObject(i)
                val propertyName: String = metaData.getColumnLabel(i)
                val field: Field = clazz.getDeclaredField(propertyName)
                field.isAccessible = true
                field.set(data, value)
            }
            list.add(data)
        }
        resultSet.close()
        prepareStatement.close()
        if (connection.autoCommit){
            JDBCUtil1.freeConnection()
        }
        return list
    }
}

private object JDBCUtil1 {

    private val threadLocal: ThreadLocal<Connection> = ThreadLocal<Connection>()
    private val dataSource: DataSource by lazy {
        val properties = Properties()
        properties.load(javaClass.getResourceAsStream("/jdbc.properties"))
        DruidDataSourceFactory.createDataSource(properties)
    }

    val connection: Connection
        get() {
            var connection: Connection? = threadLocal.get()
            if (connection == null){
                connection = dataSource.connection
                threadLocal.set(connection)
            }
            return connection!!
        }
    fun freeConnection(){
        threadLocal.get()?.apply {
            threadLocal.remove()
            autoCommit = true
            close()
        }
    }
}