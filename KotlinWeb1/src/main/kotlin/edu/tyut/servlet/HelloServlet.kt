package edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "HelloServlet", value = ["/HelloServlet"])
class HelloServlet : HttpServlet(){
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("Hello Kotlin Servlet...")
        println("servletPath: ${request?.servletPath}")
        println("contextPath: ${request?.servletContext?.contextPath}")
        println("接收参数 -> username: ${request?.getParameter("username")}")
        response?.writer?.println("接受参数成功 -> username:${request?.getParameter("username")}")
    }
}