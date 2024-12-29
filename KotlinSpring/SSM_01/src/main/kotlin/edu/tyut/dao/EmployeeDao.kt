package edu.tyut.dao

import edu.tyut.bean.Employee

interface EmployeeDao {
    fun getAllEmployee(): List<Employee>
    fun getEmployeeById(empId: Int): Employee
    fun addEmployee(employee: Employee): Int
    fun updateEmployee(employee: Employee): Int
    fun deleteEmployeeById(empId: Int): Int
}