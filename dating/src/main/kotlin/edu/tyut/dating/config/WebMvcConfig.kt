package edu.tyut.dating.config

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.cglib.proxy.Dispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet

@Configuration
internal class WebMvcConfig {

    private final val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
     internal fun dispatcherServletRegister(dispatcherServlet: DispatcherServlet): ServletRegistrationBean<DispatcherServlet> {
        return ServletRegistrationBean(dispatcherServlet, "/")
    }
}