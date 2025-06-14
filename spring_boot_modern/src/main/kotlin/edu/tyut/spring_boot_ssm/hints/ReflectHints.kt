package edu.tyut.spring_boot_ssm.hints

import org.jetbrains.exposed.v1.core.UIntegerColumnType
import org.springframework.aot.hint.ExecutableHint
import org.springframework.aot.hint.ExecutableMode
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.TypeHint
import org.springframework.aot.hint.TypeReference
import org.springframework.aot.hint.registerType

internal class ReflectHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        // Java Method
        // hints.reflection().registerType(Result.Companion::class.java){ typeHintBuilder: TypeHint.Builder ->
        //     typeHintBuilder.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)
        // }
        // Kotlin Extension Method
        // no use...

        // hints.reflection().registerType<ResolverConfigurationImpl> { typeHintBuilder: TypeHint.Builder ->
        //     typeHintBuilder.withMembers( MemberCategory.INVOKE_DECLARED_CONSTRUCTORS)
        // }

        hints.reflection().registerType<UInt> { typeHintBuilder: TypeHint.Builder ->
            typeHintBuilder.withMethod("constructor-impl", listOf<TypeReference>(TypeReference.of(Int::class::javaPrimitiveType.get()!!)), ExecutableMode.INVOKE)
            typeHintBuilder.withMethod("box-impl", listOf<TypeReference>(TypeReference.of(Int::class::javaPrimitiveType.get()!!)), ExecutableMode.INVOKE)
            typeHintBuilder.withMethod("unbox-impl", listOf<TypeReference>(), ExecutableMode.INVOKE)
        }
        hints.reflection().registerType<UIntegerColumnType>{ typeHintBuilder: TypeHint.Builder ->
            typeHintBuilder.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS)
        }
        hints.reflection().registerType<edu.tyut.spring_boot_ssm.validation.StateValidation>{ typeHintBuilder: TypeHint.Builder ->
            typeHintBuilder.withMembers(
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS
            )
        }
    }
}
