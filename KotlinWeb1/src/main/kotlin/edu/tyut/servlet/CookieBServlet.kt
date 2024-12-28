package edu.tyut.edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "CookieBServlet", value = ["/CookieBServlet"])
class CookieBServlet : HttpServlet() {

    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        request?.cookies?.forEach {
            println("cookie -> name: ${it.name}, value: ${it.value}")
        }
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}