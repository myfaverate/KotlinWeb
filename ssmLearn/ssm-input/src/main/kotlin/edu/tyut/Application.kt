package edu.tyut

import edu.tyut.config.MvcConfig
import org.apache.catalina.Context
import org.apache.catalina.Wrapper
import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet


private val logger: Logger = LoggerFactory.getLogger("Application")

private fun main() {

    val isDev: Boolean = true

    System.setProperty(kotlinx.coroutines.DEBUG_PROPERTY_NAME, if (isDev) kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON else kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF)

    SLF4JBridgeHandler.removeHandlersForRootLogger()
    SLF4JBridgeHandler.install()

    val port = 8080
    val contextPath = ""
    val tomcatBaseDir = "build/tomcat"

    val tomcat = Tomcat()
    tomcat.setPort(port)
    tomcat.setBaseDir(tomcatBaseDir)

    val context: Context = tomcat.addContext(contextPath, null)


    logger.info("Starting tomcat on Port: {}", port)
    logger.info("Starting tomcat on contextPath: {}", contextPath)
    logger.info("Starting tomcat on tomcatBaseDir: {}", tomcatBaseDir)


    // 手动初始化 Spring Web 容器
    val appContext = AnnotationConfigWebApplicationContext()
    appContext.register(MvcConfig::class.java)

    val dispatcher = DispatcherServlet(appContext)
    val servlet: Wrapper = Tomcat.addServlet(context, "dispatcher", dispatcher)
    servlet.isAsyncSupported = true
    servlet.loadOnStartup = 1
    servlet.addMapping("/")

    tomcat.start()
    val connector: Connector = tomcat.connector
    logger.info("Starting success connector: $connector ...")
    tomcat.server.await()
}