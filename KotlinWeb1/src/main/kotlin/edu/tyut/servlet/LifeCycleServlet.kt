package edu.tyut.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "LifeCycleServlet", value = ["/LifeCycleServlet"])
class LifeCycleServlet : HttpServlet() {

    init {
        println("LifeCycleServlet constructor init...")
    }

    override fun init() {
        super.init()
        println("LifeCycleServlet  init...")
    }

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("LifeCycleServlet service...")
        resp?.writer?.println("Hello World!")

    }

    override fun destroy() {
        super.destroy()
        println("LifeCycleServlet destroy...")
    }
}