package edu.tyut.controller

import edu.tyut.bean.Employee
import edu.tyut.service.EmployeeService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
internal class EmployeeController(
    private val employeeService: EmployeeService
) {
    @GetMapping(value = ["/employees"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun employees(): List<Employee> {
        return employeeService.findAll().await()
    }
}