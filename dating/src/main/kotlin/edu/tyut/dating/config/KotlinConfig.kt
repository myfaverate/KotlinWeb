package edu.tyut.dating.config

import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

@Configuration
internal class KotlinConfig {
    private final val logger = LoggerFactory.getLogger(this.javaClass)
    @Bean
    internal fun kotlinMessageConverter(): KotlinSerializationJsonHttpMessageConverter {
        val json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
        logger.info("Kotlin SerializationJsonHttpMessageConverter: $json")
        return KotlinSerializationJsonHttpMessageConverter(json)
    }
}