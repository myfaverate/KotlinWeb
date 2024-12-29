package edu.tyut.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import edu.tyut.bean.Employee
import edu.tyut.dao.EmployeeDao
import edu.tyut.service.HelloService
import org.springframework.stereotype.Service

@Service
class HelloServiceImpl(
    private val employeeDao: EmployeeDao
) : HelloService {
    override fun hello(): String {
        return "Hello World Service"
    }

    override fun getAllEmployee(): List<Employee> {
        return employeeDao.getAllEmployee()
    }

    override fun getEmployeeById(empId: Int): Employee {
        return employeeDao.getEmployeeById(empId = empId)
    }

    override fun getEmployeePage(pageNum: Int): PageInfo<Employee> {
        // 开启分页功能
        PageHelper.startPage<Employee>(pageNum, 4)
        val employeeList: List<Employee> = employeeDao.getAllEmployee()
        val pageInfo: PageInfo<Employee> = PageInfo(employeeList, 5)
        return pageInfo
    }

    override fun addEmployee(employee: Employee): Int {
        return employeeDao.addEmployee(employee)
    }

    override fun updateEmployee(employee: Employee): Int {
        return employeeDao.updateEmployee(employee)
    }

    override fun deleteEmployeeById(epmId: Int): Int {
        return employeeDao.deleteEmployeeById(epmId)
    }

}