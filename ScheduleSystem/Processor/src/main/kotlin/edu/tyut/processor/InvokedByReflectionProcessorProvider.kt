package edu.tyut.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import edu.tyut.annoation.InvokedByReflection

private class InvokedByReflectionProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        // 输出传入参数
        println("InvokedByReflectionProcessor...")
        environment.logger.warn("map ${environment.options}")
        val symbols: Sequence<KSAnnotated> = resolver.getSymbolsWithAnnotation(InvokedByReflection::class.qualifiedName!!)
        symbols.filterIsInstance<KSFunctionDeclaration>().forEach { ksFunctionDeclaration: KSFunctionDeclaration ->
            environment.logger.warn(message = "Found function annotated with @InvokedByReflection: ${ksFunctionDeclaration.simpleName.asString()}")
        }
        return emptyList()
    }
}

class InvokedByReflectionProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return InvokedByReflectionProcessor(environment = environment)
    }

}