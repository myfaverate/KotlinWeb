package edu.tyut.dating.hints

import edu.tyut.dating.bean.Result
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.TypeHint
import org.springframework.aot.hint.registerType

internal class ReflectHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        // Java Method
        // hints.reflection().registerType(Result.Companion::class.java){ typeHintBuilder: TypeHint.Builder ->
        //     typeHintBuilder.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)
        // }
        // Kotlin Extension Method
        hints.reflection().registerType<Result.Companion> { typeHintBuilder: TypeHint.Builder ->
            typeHintBuilder.withMembers( MemberCategory.INVOKE_DECLARED_METHODS)
        }
    }
}