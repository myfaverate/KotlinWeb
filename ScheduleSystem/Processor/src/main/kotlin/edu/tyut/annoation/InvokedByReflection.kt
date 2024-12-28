package edu.tyut.annoation

@Retention(value = AnnotationRetention.SOURCE) // 仅在源码时提示
@Target(AnnotationTarget.FUNCTION) // 仅限于方法
annotation class InvokedByReflection(
    val reason: String = "This method is invoked via reflection.",
)
