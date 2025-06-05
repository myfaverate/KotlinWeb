package edu.tyut

import edu.tyut.config.ExposedConfig
import edu.tyut.config.SpringConfig
import edu.tyut.config.WebMvcConfig
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import org.apache.catalina.Context
import org.apache.catalina.Wrapper
import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet


private val logger: Logger = LoggerFactory.getLogger("Application")

// 两个 bean 容器
// private fun main() {
//
//     val isDev: Boolean = true
//
//     System.setProperty(DEBUG_PROPERTY_NAME, if (isDev) DEBUG_PROPERTY_VALUE_ON else DEBUG_PROPERTY_VALUE_OFF)
//
//     SLF4JBridgeHandler.removeHandlersForRootLogger()
//     SLF4JBridgeHandler.install()
//
//     val port = 8080
//     val contextPath = ""
//     val tomcatBaseDir = "build/tomcat"
//
//     val tomcat = Tomcat()
//     tomcat.setPort(port)
//     tomcat.setBaseDir(tomcatBaseDir)
//
//     val context: Context = tomcat.addContext(contextPath, null)
//
//
//     logger.info("Starting tomcat on Port: {}", port)
//     logger.info("Starting tomcat on contextPath: {}", contextPath)
//     logger.info("Starting tomcat on tomcatBaseDir: {}", tomcatBaseDir)
//
//
//     // 1️⃣ 创建 Root ApplicationContext
//     val rootContext = AnnotationConfigWebApplicationContext()
//     rootContext.register(SpringConfig::class.java, ExposedConfig::class.java)
//
//     // 2️⃣ 添加 ContextLoaderListener，注册 Root 容器
//     rootContext.refresh()
//     context.servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootContext)
//
//     // 3️⃣ 创建 DispatcherServlet 的 WebApplicationContext（子容器）
//     val webContext = AnnotationConfigWebApplicationContext()
//     webContext.register(WebMvcConfig::class.java)
//     webContext.parent = rootContext
//
//
//     val dispatcher = DispatcherServlet(webContext)
//     val servlet: Wrapper = Tomcat.addServlet(context, "dispatcher", dispatcher)
//     val defaultServlet: Wrapper = context.createWrapper().apply {
//         name = "default"
//         servletClass = "org.apache.catalina.servlets.DefaultServlet"
//         loadOnStartup = 1
//     }
//     context.addChild(defaultServlet)
//     context.addServletMappingDecoded("/", "default")
//     servlet.isAsyncSupported = true
//     servlet.loadOnStartup = 1
//     servlet.addMapping("/")
//
//     tomcat.start()
//     val connector: Connector = tomcat.connector
//     logger.info("Starting success connector: $connector ...")
//     tomcat.server.await()
// }

// 一个容器
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
    // appContext.register(WebMvcConfig::class.java, SpringConfig::class.java, ExposedConfig::class.java)
    appContext.register(WebMvcConfig::class.java)

    val dispatcher = DispatcherServlet(appContext)
    val servlet: Wrapper = Tomcat.addServlet(context, "dispatcher", dispatcher)
    val defaultServlet: Wrapper = context.createWrapper().apply {
        name = "default"
        servletClass = "org.apache.catalina.servlets.DefaultServlet"
        loadOnStartup = 1
    }
    context.addChild(defaultServlet)
    context.addServletMappingDecoded("/", "default")
    servlet.isAsyncSupported = true
    servlet.loadOnStartup = 1
    servlet.addMapping("/")

    tomcat.start()
    val connector: Connector = tomcat.connector
    logger.info("Starting success connector: $connector ...")
    tomcat.server.await()
}