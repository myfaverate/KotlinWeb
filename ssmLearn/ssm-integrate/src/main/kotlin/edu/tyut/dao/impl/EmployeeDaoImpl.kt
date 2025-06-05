package edu.tyut.dao.impl

import edu.tyut.bean.Employee
import edu.tyut.dao.EmployeeDao
import edu.tyut.entity.EmployeeEntity
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.experimental.suspendedTransactionAsync
import org.springframework.stereotype.Repository

@Repository
internal class EmployeeDaoImpl : EmployeeDao {
    override suspend fun findAll(): Deferred<List<Employee>> = suspendedTransactionAsync  {
        EmployeeEntity.selectAll().map { resultRow: ResultRow ->
            Employee(
                id = resultRow[EmployeeEntity.id].value,
                empName = resultRow[EmployeeEntity.empName],
                age = resultRow[EmployeeEntity.age],
                gender = resultRow[EmployeeEntity.gender],
                email = resultRow[EmployeeEntity.email]
            )
        }
    }
}