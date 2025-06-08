package edu.tyut.spring_boot_ssm.serializer

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.ContextualSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.format.DateTimeFormatter

internal class KtxLocalDateTimeSerializer(
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
) : JsonSerializer<LocalDateTime>(), ContextualSerializer {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun serialize(
        value: LocalDateTime?,
        gen: JsonGenerator?,
        serializers: SerializerProvider?
    ) {
        if (null == gen) {
            gen?.writeNull()
        }else{
            val javaTime: java.time.LocalDateTime? = value?.toJavaLocalDateTime()
            gen.writeString(javaTime?.format(formatter))
        }
    }

    override fun createContextual(
        prov: SerializerProvider?,
        property: BeanProperty?
    ): JsonSerializer<*>? {
        if (null != prov) {
            val format: JsonFormat = property?.getAnnotation<JsonFormat>(JsonFormat::class.java) ?: return null
            if(format.pattern.isNotEmpty()){
                logger.info("pattern: {}", format.pattern)
                return KtxLocalDateTimeSerializer(formatter = DateTimeFormatter.ofPattern(format.pattern))
            }
        }
        return this
    }
}