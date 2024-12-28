package edu.tyut

import com.alibaba.druid.pool.DruidDataSourceFactory
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.sql.*
import java.util.*
import javax.sql.DataSource


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ColumnName(val value: String)

open class BaseDao {
    fun executeUpdate(sql: String, vararg params: Any): Int {
        val connection: Connection = JDBCUtil.connection
        try {
            connection.prepareStatement(sql).use { prepareStatement: PreparedStatement ->
                for (i in 1..params.size) {
                    prepareStatement.setObject(i, params[i - 1])
                }
                val rows: Int = prepareStatement.executeUpdate()
                return rows
            }
        } catch (e: SQLException) {
            throw e
        } finally {
            if (connection.autoCommit) {
                JDBCUtil.freeConnection()
            }
        }
    }

    fun <T> executeQuery(clazz: Class<T>, sql: String, vararg params: Any): List<T> {
        val list: MutableList<T> = mutableListOf()
        val connection: Connection = JDBCUtil.connection
        try {
            connection.prepareStatement(sql).use { preparedStatement: PreparedStatement ->
                for (i in 1..params.size) {
                    preparedStatement.setObject(i, params[i - 1])
                }
                preparedStatement.executeQuery().use { resultSet: ResultSet ->
                    // 有注解拿注解没注解拿字段
                    val propertyClazzList: List<Pair<String, Class<*>>> = clazz.declaredFields.map { field: Field ->
                        val annotation: ColumnName? = field.getAnnotation(ColumnName::class.java)
                        (annotation?.value ?: field.name) to field.type
                    }
                    clazz.declaredConstructors.map { it.parameterTypes }.maxBy { it.size }.first()
                    val constructor: Constructor<T> =
                        clazz.getDeclaredConstructor(*(propertyClazzList.map { it.second }.toTypedArray()))
                    while (resultSet.next()) {
                        val values: MutableList<Any> = mutableListOf()
                        propertyClazzList.forEach {
                            values.add(resultSet.getObject(it.first))
                        }
                        val data: T = constructor.newInstance(*(values.toTypedArray()))
                        list.add(data)
                    }
                }
            }
        } catch (e: SQLException) {
            throw e
        } finally {
            if (connection.autoCommit) {
                JDBCUtil.freeConnection()
            }
        }
        return list
    }
}

private object JDBCUtil {

    private val threadLocal: ThreadLocal<Connection> = ThreadLocal<Connection>()
    private val dataSource: DataSource by lazy {
        val properties = Properties()
        properties.load(javaClass.getResourceAsStream("/jdbc.properties"))
        DruidDataSourceFactory.createDataSource(properties)
    }

    val connection: Connection
        get() {
            var connection: Connection? = threadLocal.get()
            if (connection == null) {
                connection = dataSource.connection
                threadLocal.set(connection)
            }
            return connection!!
        }

    fun freeConnection() {
        threadLocal.get()?.apply {
            threadLocal.remove()
            autoCommit = true
            close()
        }
    }
}