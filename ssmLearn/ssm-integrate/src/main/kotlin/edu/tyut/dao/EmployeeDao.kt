package edu.tyut.dao

import edu.tyut.bean.Employee
import kotlinx.coroutines.Deferred

internal interface EmployeeDao {
    suspend fun findAll(): Deferred<List<Employee>>
}