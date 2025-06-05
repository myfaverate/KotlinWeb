package edu.tyut.service

import edu.tyut.bean.Employee
import kotlinx.coroutines.Deferred

internal interface EmployeeService {
    suspend fun findAll(): Deferred<List<Employee>>
}