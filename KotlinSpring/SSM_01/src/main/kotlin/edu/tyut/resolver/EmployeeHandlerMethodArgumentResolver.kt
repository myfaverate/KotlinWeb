package edu.tyut.resolver

import edu.tyut.bean.Employee
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class EmployeeHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == Employee::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        logger.info("EmployeeHandlerMethodArgumentResolver...")
        val httpServletRequest: HttpServletRequest? = webRequest.getNativeRequest<HttpServletRequest>(HttpServletRequest::class.java)
        val defaultEmployee = Employee()
        httpServletRequest?.apply {
            val empId: Int = webRequest.getParameter("emp_id")?.toIntOrNull() ?: 0
            val empName: String? = getParameter("emp_name")
            val empAge: Int = getParameter("emp_age")?.toIntOrNull() ?: 0
            val empGender: String? = getParameter("emp_gender")
            val empEmail: String? = getParameter("emp_email")
            logger.info("EmployeeHandlerMethodArgumentResolver empId: $empId, empName: $empName, empAge: $empAge, empGender: $empGender, empEmail: $empEmail")
            if (!empName.isNullOrEmpty() && !empGender.isNullOrEmpty() && !empEmail.isNullOrEmpty()) {
                return Employee(empId = empId, empName = empName, age = empAge, gender = empGender, email = empEmail)
            }
        }
        return defaultEmployee
    }
}