package edu.tyut.filter

import edu.tyut.bean.Result
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@WebFilter(value = ["/*"])
class AllowOriginFilter : Filter{
    override fun doFilter(
        request: ServletRequest?,
        response: ServletResponse?,
        chain: FilterChain?
    ) {
        val request: HttpServletRequest? = request as? HttpServletRequest
        val response: HttpServletResponse? = response as? HttpServletResponse
        response?.setHeader("Access-Control-Allow-Origin", "*")
        response?.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD")
        response?.setHeader("Access-Control-Max-Age", "3600")
        response?.setHeader("Access-Control-Allow-Headers", "*")
        if (request?.method == "OPTIONS") {
            val result: String = Json.encodeToString(Result.success("预检成功", 0))
            response?.writer?.write(result)
        }else{
            chain?.doFilter(request, response)
        }
    }
}