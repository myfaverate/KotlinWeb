package edu.tyut.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.context.ContextLoader
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@EnableWebMvc
@Configuration
@ComponentScan(value = ["edu.tyut.controller"])
internal open class MvcConfig : CoroutinesWebMvcConfigurer() {
    private val logger: Logger = LoggerFactory.getLogger(MvcConfig::class.java)

    override fun extendMessageConverters(converters: List<HttpMessageConverter<*>?>) {
        for (converter in converters) {
            if (converter is StringHttpMessageConverter) {
                converter.defaultCharset = Charsets.UTF_8
            }
            logger.info("extendMessageConverters -> ${converter?.javaClass}")
        }
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        logger.info("viewResolver....")
        val viewPath: String? = ContextLoader.getCurrentWebApplicationContext()?.servletContext?.getRealPath("views")
        logger.info("viewsPath : $viewPath")
        val springResourceTemplateResolver: ClassLoaderTemplateResolver = ClassLoaderTemplateResolver().apply {
            prefix = "templates/"
            suffix = ".html"
            characterEncoding = "UTF-8"
            templateMode = TemplateMode.HTML
        }
        val springTemplateEngine: SpringTemplateEngine = SpringTemplateEngine().apply {
            setTemplateResolver(springResourceTemplateResolver)
        }
        val thymeleafViewResolver = ThymeleafViewResolver()
        thymeleafViewResolver.templateEngine = springTemplateEngine
        thymeleafViewResolver.characterEncoding = "UTF-8"
        registry.viewResolver(thymeleafViewResolver)

        // ========= jsp
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/")
            .setCachePeriod(3600)
    }

}