package edu.tyut.spring_boot_ssm.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.introspect.VisibilityChecker
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
internal class JacksonConfig(
    private val objectMapper: ObjectMapper
) {
    @PostConstruct
    internal fun objectMapper(): ObjectMapper {
        return objectMapper.apply {
            setVisibility(
                VisibilityChecker.Std.defaultInstance()
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            )
        }
    }
}