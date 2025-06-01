package edu.tyut.dating.config

import jakarta.servlet.MultipartConfigElement
import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet

@Configuration
internal class WebMvcConfig {

    private final val logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
     internal fun dispatcherServletRegister(dispatcherServlet: DispatcherServlet): ServletRegistrationBean<DispatcherServlet> {
        return ServletRegistrationBean(dispatcherServlet, "/").apply {
            multipartConfig = MultipartConfigElement(System.getProperty("java.io.tmpdir").apply {
                logger.info("Using java.io.tmpdir: $this")
            })
        }
    }
}