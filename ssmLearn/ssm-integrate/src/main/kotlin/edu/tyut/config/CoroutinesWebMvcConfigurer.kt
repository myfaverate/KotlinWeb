package edu.tyut.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.*
import org.springframework.core.MethodParameter
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler
import java.lang.annotation.Inherited
import java.lang.reflect.Method
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.reflect.jvm.kotlinFunction

private const val DEFERRED_RESULT: String = "deferred_result"

private val Method.isSuspend: Boolean
    get() = kotlinFunction?.isSuspend ?: false

private open class OnClassCondition : Condition {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean =
        try {
            (metadata.getAnnotationAttributes(ConditionalOnClass::class.java.name)
                ?.get(key = "value") as? String)?.let { className: String ->
                Class.forName(className)
            }
            true
        } catch (e: ClassNotFoundException) {
            logger.error("Class not found", e)
            false
        }
}

@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
@Retention(value = AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Conditional(value = [OnClassCondition::class])
private annotation class ConditionalOnClass(
    val value: String = ""
)

@Configuration
@ConditionalOnClass(value = "org.springframework.web.servlet.config.annotation.WebMvcConfigurer")
@Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
internal open class CoroutinesWebMvcConfigurer : WebMvcConfigurer {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {

        argumentResolvers.add(index = 0, element = object : HandlerMethodArgumentResolver {

            override fun supportsParameter(parameter: MethodParameter) =
                parameter.method?.isSuspend == true && isContinuationClass(clazz = parameter.parameterType)

            override fun resolveArgument(
                parameter: MethodParameter, mavContainer: ModelAndViewContainer?,
                webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?
            ) = object : Continuation<Any> {
                    val deferredResult = DeferredResult<Any>()

                    override val context: CoroutineContext
                        get() = EmptyCoroutineContext

                    override fun resumeWith(result: Result<Any>) {
                        if (result.isSuccess) {
                            deferredResult.result = result.getOrNull()
                        } else {
                            deferredResult.setErrorResult(result.exceptionOrNull() ?: Exception("Unexpected error"))
                        }
                    }
                }.apply {
                    mavContainer?.model[DEFERRED_RESULT] = deferredResult
                }
        })
    }

    override fun addReturnValueHandlers(returnValueHandlers: MutableList<HandlerMethodReturnValueHandler>) {

        returnValueHandlers.add(index = 0, element = object : AsyncHandlerMethodReturnValueHandler {

            private val delegate = DeferredResultMethodReturnValueHandler()

            override fun supportsReturnType(returnType: MethodParameter): Boolean =
                returnType.method?.isSuspend == true

            override fun handleReturnValue(
                returnValue: Any?, type: MethodParameter,
                mavContainer: ModelAndViewContainer, webRequest: NativeWebRequest
            ) {

                val result: DeferredResult<*> = mavContainer.model[DEFERRED_RESULT] as DeferredResult<*>

                return delegate.handleReturnValue(result, type, mavContainer, webRequest)
            }

            override fun isAsyncReturnValue(returnValue: Any?, returnType: MethodParameter): Boolean =
                returnValue === COROUTINE_SUSPENDED
        })
    }

    private fun <T> isContinuationClass(clazz: Class<T>) = Continuation::class.java.isAssignableFrom(clazz)

}
