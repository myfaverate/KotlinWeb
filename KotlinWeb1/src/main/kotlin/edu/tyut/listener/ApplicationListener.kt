package edu.tyut.edu.tyut.listener

import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener

@WebListener
class ApplicationListener : ServletContextListener{
    override fun contextInitialized(sce: ServletContextEvent?) {
        println("${sce?.servletContext}: ApplicationListener contextInitialized...")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        println("${sce?.servletContext}: ApplicationListener contextDestroyed...")
    }

}