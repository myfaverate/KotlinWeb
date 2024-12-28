package edu.tyut.filter

import edu.tyut.bean.SysUser
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

// @WebFilter(urlPatterns = ["/static/page/showSchedule.html"], servletNames = ["SysScheduleController"])
class LoginFilter : Filter{

    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {
        val request: HttpServletRequest? = servletRequest as? HttpServletRequest
        val response: HttpServletResponse? = servletResponse as? HttpServletResponse
        val user: SysUser? = request?.session?.getAttribute("user") as? SysUser
        if (user == null) {
            response?.sendRedirect("/static/page/login.html")
        }else{
            chain?.doFilter(servletRequest, servletResponse)
        }
    }

}