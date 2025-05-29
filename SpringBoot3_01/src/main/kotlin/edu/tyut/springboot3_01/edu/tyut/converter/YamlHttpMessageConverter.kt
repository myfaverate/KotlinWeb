package edu.tyut.springboot3_01.edu.tyut.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.yaml.snakeyaml.Yaml

class YamlHttpMessageConverter : AbstractHttpMessageConverter<Any>(MediaType("application", "yaml", Charsets.UTF_8)) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun supports(clazz: Class<*>): Boolean {
        return true
    }

    override fun readInternal(
        clazz: Class<out Any?>,
        inputMessage: HttpInputMessage
    ): Any {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(KotlinModule.Builder().build())
        inputMessage.body.use {
            return objectMapper.readValue(inputMessage.body, clazz).apply {
                logger.info("readInternal: $this")
            }
        }
    }

    override fun writeInternal(messageReturValue: Any, outputMessage: HttpOutputMessage) {
        val yamlFactory = YAMLFactory()
        yamlFactory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        val objectMapper = ObjectMapper(yamlFactory)
        logger.info("writeInternal: ${objectMapper.writeValueAsString(messageReturValue)}")
        outputMessage.body.use {
            objectMapper.writeValue(it, messageReturValue)
        }
    }
}
/**
!!edu.tyut.springboot3_01.edu.tyut.bean.User {age: 17, password: '1234455', salary: 10,
username: 张书豪}

 */