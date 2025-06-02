package edu.tyut.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

internal class WebInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    /**
     * spring 的 bean 容器 dao、service
     * springmvc 的 bean 容器 controller
     */
    override fun getRootConfigClasses(): Array<out Class<*>?>? {
        return null
    }

    /**
     * web springmvc 的 bean 容器 controller
     */
    override fun getServletConfigClasses(): Array<out Class<*>?>? {
        return arrayOf<Class<MvcConfig>>(MvcConfig::class.java)
    }

    /**
     * DispatcherServlet 处理路径 / 替换 web.xml ...
     */
    override fun getServletMappings(): Array<out String?> {
        return arrayOf<String>("/")
    }

}