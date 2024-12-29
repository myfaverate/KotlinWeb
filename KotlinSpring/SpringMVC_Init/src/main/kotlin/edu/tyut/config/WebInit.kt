package edu.tyut.config

import jakarta.servlet.Filter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class WebInit : AbstractAnnotationConfigDispatcherServletInitializer() {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Spring配置文件
     */
    override fun getRootConfigClasses(): Array<out Class<*>?>? {
        logger.info("getRootConfigClasses...")
        return arrayOf(SpringConfig::class.java)
    }

    /**
     * Spring MVC 配置文件
     */
    override fun getServletConfigClasses(): Array<out Class<*>?>? {
        logger.info("getServletConfigClasses...")
        return arrayOf(WebConfig::class.java)
    }

    /**
     * utl-pattern
     */
    override fun getServletMappings(): Array<out String?> {
        logger.info("getServletMappings...")
        return arrayOf("/")
    }

    override fun getServletFilters(): Array<out Filter?>? {
        val characterEncodingFilter = CharacterEncodingFilter(Charsets.UTF_8.name(), true)
        return arrayOf(characterEncodingFilter)
    }
}