package edu.tyut.annotation

@Target(allowedTargets = [AnnotationTarget.CLASS]) // 限制可用于的目标
@Retention(value = AnnotationRetention.RUNTIME) // 定义注解的保留策略
@MustBeDocumented // 表示生成文档时包含注解
annotation class Logger
