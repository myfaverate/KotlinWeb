package edu.tyut.filter

import edu.tyut.servlet.HelloServlet
import jakarta.servlet.*
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.annotation.WebInitParam

@WebFilter(urlPatterns = ["/**"], initParams = [WebInitParam(name = "zsh", value = "true")], servletNames = ["HelloServlet"])
class LoggingFilter : Filter {


    constructor(){
        println("LoggingFilter constructor...")
    }

    override fun init(filterConfig: FilterConfig?) {
        println("LoggingFilter init name: ${filterConfig?.getInitParameter("name")}...")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("request -> filter....")
        chain?.doFilter(request, response)
        println("response -> filter...")
    }

    override fun destroy() {
        super.destroy()
        println("LoggingFilter destroy...")
    }
}