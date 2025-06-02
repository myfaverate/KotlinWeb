package edu.tyut

import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

private val logger: Logger = LoggerFactory.getLogger("Application")

internal fun main() {

    val port = 8080
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
    tomcat.addWebapp(contextPath, webAppDir.absolutePath)

    tomcat.start()
    val connector: Connector = tomcat.connector
    logger.info("Starting success connector: $connector ...")
    tomcat.server.await()
}