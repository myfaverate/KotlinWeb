package edu.tyut.edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "CookieAServlet", value = ["/CookieAServlet"])
class CookieAServlet : HttpServlet() {
    override fun doGet(request: HttpServletRequest?, response: HttpServletResponse?) {
        response?.addCookie(Cookie("name", "Tom").apply {
            maxAge = 60
            path = "/KotlinWeb1/CookieBServlet"
        })
        response?.addCookie(Cookie("age", "18"))
    }

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        doGet(request = request, response = response)
    }
}