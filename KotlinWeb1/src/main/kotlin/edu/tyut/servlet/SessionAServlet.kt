package edu.tyut.edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "SessionAServlet", value = ["/SessionAServlet"])
class SessionAServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        val username: String? = request?.getParameter("username")
        println("SessionAServlet -> username: $username")
        println(request?.session?.isNew)
        println(request?.session?.id)
        request?.session?.setAttribute("zsh", username)
        response?.contentType = "text/html;charset=UTF-8"
        response?.writer?.println("成功!")
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}