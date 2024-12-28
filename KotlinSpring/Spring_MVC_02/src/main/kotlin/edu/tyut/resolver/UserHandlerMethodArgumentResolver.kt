package edu.tyut.resolver

import edu.tyut.edu.tyut.bean.User
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == User::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        logger.info("UserHandlerMethodArgumentResolver...")
        val httpServletRequest: HttpServletRequest? = webRequest.getNativeRequest<HttpServletRequest>(HttpServletRequest::class.java)
        val defaultUser = User()
        httpServletRequest?.apply {
            val username: String? = getParameter("userName")
            val password: String? = getParameter("passWord")
            logger.info("UserHandlerMethodArgumentResolver username: $username, password : $password")
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                return User(username, password)
            }
        }
        return defaultUser
    }
}