package edu.tyut.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.context.ContextLoader
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * 代替 SpringMVC 的配置文件
 */
@Configuration
@ComponentScan(value = ["edu.tyut.controller"])
@EnableWebMvc // 注解驱动
open class WebConfig : WebMvcConfigurer{
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    /**
     * 默认静态资源
     */
    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        logger.info("configureDefaultServletHandling...")
        configurer.enable()
    }

    override fun extendMessageConverters(converters: List<HttpMessageConverter<*>?>) {
        for (converter in converters) {
            if (converter is StringHttpMessageConverter) {
                converter.defaultCharset = Charsets.UTF_8
            }
            logger.info("extendMessageConverters -> ${converter?.javaClass}")
        }
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        logger.info("addViewControllers...")
        registry.addViewController("/").setViewName("index")
    }

    override fun addArgumentResolvers(resolvers: List<HandlerMethodArgumentResolver?>) {
        logger.info("addArgumentResolvers...")
        super.addArgumentResolvers(resolvers)
    }

    @Bean
    open fun viewResolver(): ViewResolver {
        logger.info("viewResolver....")
        val viewPath: String? = ContextLoader.getCurrentWebApplicationContext()?.servletContext?.getRealPath("views")
        logger.info("viewsPath : $viewPath")
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/views/")
        viewResolver.setSuffix(".jsp")
        return viewResolver
    }
}