package edu.tyut.service.impl

import edu.tyut.bean.Employee
import edu.tyut.dao.EmployeeDao
import edu.tyut.service.EmployeeService
import kotlinx.coroutines.Deferred
import org.springframework.stereotype.Service

@Service
internal class EmployeeServiceImpl(
    private val employeeDao: EmployeeDao
) : EmployeeService {
    override suspend fun findAll(): Deferred<List<Employee>> {
        return employeeDao.findAll()
    }
}