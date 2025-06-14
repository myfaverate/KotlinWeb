package edu.tyut.spring_boot_ssm.hints

import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar

internal class ResourceHints internal constructor(): RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        // val resource = ClassPathResource("email-config.yml")
        // hints.resources().registerResource(resource)
    }
}
