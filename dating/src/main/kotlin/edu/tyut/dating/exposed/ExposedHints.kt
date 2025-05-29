package edu.tyut.dating.exposed

import org.jetbrains.exposed.v1.core.dao.id.EntityIDFactory
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar

internal class ExposedHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.reflection().registerType(EntityIDFactory::class.java, *MemberCategory.entries.toTypedArray())
    }
}