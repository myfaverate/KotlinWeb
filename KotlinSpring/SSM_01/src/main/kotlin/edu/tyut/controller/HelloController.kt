package edu.tyut.controller

import edu.tyut.bean.Employee
import edu.tyut.service.HelloService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.view.RedirectView

@Controller
class HelloController(
    private val helloService: HelloService
){

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/hello"])
    @ResponseBody
    fun hello(): String {
        return helloService.hello()
    }

    @GetMapping(value = ["/employee"])
    fun getAllEmployees(model: Model): String {
        model.addAttribute("employee_list", helloService.getAllEmployee())
        return "employee_list"
    }

    @GetMapping(value = ["/employee/page/{pageNum}"])
    fun getEmployeePage(@PathVariable(value = "pageNum") pageNumber: Int, model: Model): String {
        model.addAttribute("employeePage", helloService.getEmployeePage(pageNumber).apply{
            logger.info("Employee pageInfo1: {}", this)
        })
        return "employee_page"
    }

    @PostMapping(value = ["/employee/add"])
    fun getEmployeePage(employee: Employee): String {
        logger.info("Employee getEmployeePage: {}", employee)
        val rows: Int = helloService.addEmployee(employee)
        if (rows > 0) {
            logger.info("Employee 添加成功 rows: {}", rows)
        }else{
            logger.info("Employee 添加失败 rows: {}", rows)
        }
        return "redirect:/employee/page/1"
    }
    @GetMapping(value = ["/employee/{empId}"])
    fun getEmployeeById(@PathVariable(value = "empId") empId: Int, model: Model): String {
        model.addAttribute("employee", helloService.getEmployeeById(empId).apply {
            logger.info("getEmployeeById id: {}, employee {}", empId, this)
        })
        return "employee_update"
    }
    @PutMapping(value = ["/employee/update"])
    fun updateEmployee(employee: Employee): String {
        logger.info("Employee update: {}", employee)
        val rows: Int = helloService.updateEmployee(employee)
        if (rows > 0) {
            logger.info("Employee 更新成功 rows: {}", rows)
        }else{
            logger.info("Employee 更新失败 rows: {}", rows)
        }
        return "redirect:/employee/page/1"
    }

    // @DeleteMapping(value = ["/employee/delete/{empId}"])
    // fun deleteEmployee(@PathVariable(value = "empId") empId: Int): String {
    //     logger.info("Employee delete: {}", empId)
    //     val rows: Int = helloService.deleteEmployeeById(empId)
    //     if (rows > 0) {
    //         logger.info("Employee 删除成功 rows: {}", rows)
    //     }else{
    //         logger.info("Employee 删除失败 rows: {}", rows)
    //     }
    //     return "redirect:/employee/page/1" // TODO 为什么 这个函数实现不了刷新页面 直接刷新页面
    // }

    @DeleteMapping(value = ["/employee/delete/{empId}"])
    @ResponseBody
    fun deleteEmployeeNew(@PathVariable(value = "empId") empId: Int): String {
        logger.info("Employee delete: {}", empId)
        val rows: Int = helloService.deleteEmployeeById(empId)
        if (rows > 0) {
            logger.info("Employee 删除成功 rows: {}", rows)
        }else{
            logger.info("Employee 删除失败 rows: {}", rows)
        }
        return "是否删除成功: ${rows > 0}" // 直接刷新页面
    }
}