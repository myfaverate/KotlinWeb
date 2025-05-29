package edu.tyut.dating.config

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class JettyConfig {
    @Bean
    internal fun servletWebServerFactory(): ServletWebServerFactory {
        return JettyServletWebServerFactory().apply {
            port = 8080
        }
    }
}