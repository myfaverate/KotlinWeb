package edu.tyut.edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "SessionBServlet", value = ["/SessionBServlet"])
class SessionBServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("SessionBServlet -> id: ${request?.session?.id}, isNew: ${request?.session?.isNew}")
        println("SessionBServlet -> value: ${request?.session?.getAttribute("zsh")}")
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}