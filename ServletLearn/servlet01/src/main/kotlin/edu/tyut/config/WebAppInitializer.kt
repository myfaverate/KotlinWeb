package edu.tyut.config

import jakarta.servlet.DispatcherType
import jakarta.servlet.Filter
import jakarta.servlet.FilterRegistration
import jakarta.servlet.ServletContainerInitializer
import jakarta.servlet.ServletContext
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRegistration
import jakarta.servlet.annotation.HandlesTypes
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.EnumSet

@HandlesTypes(value = [WebServlet::class, WebFilter::class])
internal class WebAppInitializer : ServletContainerInitializer {

    private val logger: Logger = LoggerFactory.getLogger(WebAppInitializer::class.java)

    @Throws(exceptionClasses = [ServletException::class])
    override fun onStartup(set: Set<Class<*>?>?, servletContext: ServletContext?) {
        logger.info("ServletContainerInitializer start set: $set")
        set?.forEach { clazz: Class<*>? ->
            clazz?.apply {
                when {
                    HttpServlet::class.java.isAssignableFrom(this) -> {
                        this.getAnnotation<WebServlet>(WebServlet::class.java)?.let { webServlet: WebServlet ->
                            val servlet: HttpServlet = this.getDeclaredConstructor().newInstance() as HttpServlet
                            val srd: ServletRegistration.Dynamic? =
                                servletContext?.addServlet(webServlet.name.ifBlank { this.simpleName }, servlet)
                            srd?.addMapping(*webServlet.urlPatterns)
                            srd?.setLoadOnStartup(1)
                            srd?.setAsyncSupported(webServlet.asyncSupported)
                            logger.info("Registered servlet: ${this.name} -> ${webServlet.urlPatterns.joinToString()}")
                        }
                    }

                    Filter::class.java.isAssignableFrom(this) -> {
                        this.getAnnotation<WebFilter>(WebFilter::class.java)?.let { webFilter: WebFilter ->
                            val filter: Filter = this.getDeclaredConstructor().newInstance() as Filter
                            val filterName: String = webFilter.filterName.ifBlank { this.simpleName }
                            val frd: FilterRegistration.Dynamic? = servletContext?.addFilter(filterName, filter)
                            frd?.addMappingForUrlPatterns(
                                EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC),
                                false,
                                *webFilter.urlPatterns
                            )
                            frd?.setAsyncSupported(webFilter.asyncSupported)
                            logger.info("Registered filter: $name -> ${webFilter.urlPatterns.joinToString()}")
                        }
                    }
                }
            }
        }
    }
}