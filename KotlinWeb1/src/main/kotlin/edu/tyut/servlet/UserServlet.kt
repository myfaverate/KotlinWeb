package edu.tyut.servlet

import jakarta.servlet.annotation.WebInitParam
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "UserServlet", value = ["/UserServlet"], initParams = [WebInitParam(name = "name", value = "张书豪")])
class UserServlet : HttpServlet() {
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        response?.contentType = "text/html;charset=UTF-8"
        val username: String = request?.getParameter("username") ?: ""
        if("zsh" == username){
            response?.writer?.println("你好 YES")
        }else{
            response?.writer?.println("你好 NO")
        }
        println(servletConfig.getInitParameter("name"))
    }
}