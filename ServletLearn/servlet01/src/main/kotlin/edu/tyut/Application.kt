package edu.tyut

import edu.tyut.servlet.UserServlet
import org.apache.catalina.Context
import org.apache.catalina.Wrapper
import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import java.io.File

private val logger: Logger = LoggerFactory.getLogger("Application")

@Deprecated(message = """
    war project use this config
    jar project use ssm-input module config
    Please refer to the ssm-input module.
""")
internal fun main() {

    SLF4JBridgeHandler.removeHandlersForRootLogger()
    SLF4JBridgeHandler.install()

    val port = 8080
    // not need change
    val contextPath = ""
    val webAppDir = File("src/main/webapp")
    val tomcatBaseDir = "build/tomcat"

    val tomcat = Tomcat()
    tomcat.setPort(port)
    tomcat.setBaseDir(tomcatBaseDir)

    logger.info("Starting tomcat on Port: {}", port)
    logger.info("Starting tomcat on contextPath: {}", contextPath)
    logger.info("Starting tomcat on webAppDir: {}", webAppDir.absolutePath)
    logger.info("Starting tomcat on tomcatBaseDir: {}", tomcatBaseDir)

    // 上下文路径
    // 添加上下文路径
    val context: Context = tomcat.addWebapp(contextPath, webAppDir.absolutePath)

    // // ✅ 手动注册 UserServlet
    // val userServlet: Wrapper = context.createWrapper().apply {
    //     name = "userServlet"
    //     servletClass = UserServlet::class.java.name
    //     loadOnStartup = 1
    // }
    // context.addChild(userServlet)
    // context.addServletMappingDecoded("/user", "userServlet")

    // 通过 spi 自动 加载

    tomcat.start()
    val connector: Connector = tomcat.connector
    logger.info("Starting success connector: $connector ...")
    tomcat.server.await()
}