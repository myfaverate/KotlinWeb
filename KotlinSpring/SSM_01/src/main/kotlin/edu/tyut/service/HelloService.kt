package edu.tyut.service

import com.github.pagehelper.PageInfo
import edu.tyut.bean.Employee

interface HelloService {
    fun hello(): String
    fun getAllEmployee(): List<Employee>
    fun getEmployeeById(empId: Int): Employee
    fun getEmployeePage(pageNum: Int): PageInfo<Employee>
    fun addEmployee(employee: Employee): Int
    fun updateEmployee(employee: Employee): Int
    fun deleteEmployeeById(epmId: Int): Int
}