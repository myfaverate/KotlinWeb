package edu.tyut

import com.mysql.cj.jdbc.Driver
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import kotlin.concurrent.thread


private fun connectDatabase(){
    // 1. 注册驱动
    DriverManager.registerDriver(Driver())
    // 2. 获取链接
    val connection: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule_system", "root", "root")
    // 3. 创建 Statement
    val statement: Statement = connection.createStatement()
    // 4. 获取结果集
    val resultSet: ResultSet = statement.executeQuery("select * from t_user")
    // 5. 解析结果集
    while (resultSet.next()) {
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


fun main(vararg args: String) {
    println(args.joinToString())
    // val baseDao = BaseDao()
    // val rows: Int = baseDao.executeUpdate("insert into t_user(account, password, nickname) values (?, ?, ?), (?, ?, ?)", "admin1", "admin1", "admin1", "admin2", "admin2", "admin2")
    // val list: List<User> = baseDao.executeQuery(User::class.java, "1select * from t_user")
    // println(list)
    // println(rows)

    data class Person private constructor(
        @ColumnName(value = "name")
        val name: String,
        val age: Int,
        @ColumnName("nickname")
        val gender: String,
    )

    Person::class.java.declaredFields.forEach { field: Field ->
        val annotation: ColumnName = field.getAnnotation(ColumnName::class.java) ?: return@forEach
        println("fieldName: ${field.name}")
        println("annotationValue: ${annotation.value}")
    }

    // Person::class.java.getDeclaredConstructor().parameterTypes
    Person::class.java.declaredConstructors.forEach { constructor ->
        constructor.parameterTypes.forEach {
            println("parameterTypes: ${it.name}")
        }
        constructor.parameters.forEach {
            println("parameterTypes: ${it.name}")
        }
    }

    val constructor: Constructor<Person> =
        Person::class.java.getDeclaredConstructor(*(Person::class.java.declaredFields.map { it.type }.toTypedArray()))

    constructor.isAccessible = true
    val person: Person = constructor.newInstance("张书豪", 18, "男")

    println(person)

}