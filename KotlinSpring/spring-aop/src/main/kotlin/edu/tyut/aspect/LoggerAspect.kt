package edu.tyut.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Aspect
class LoggerAspect {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Pointcut(value = "execution(* edu.tyut.aspect.PersonImpl.*(..))")
    fun pointCut(){}

    @Before(value = "pointCut()")
    fun before(joinPoint: JoinPoint) {
        logger.info("LoggerAspect name: ${joinPoint.signature.name}, args: ${joinPoint.args.toList()}, 前置通知...")
    }
    @AfterReturning(value = "pointCut()", returning = "result")
    fun afterReturning(joinPoint: JoinPoint, result: String) {
        logger.info("LoggerAspect name: ${joinPoint.signature.name}, args: ${joinPoint.args.toList()}, result: $result 后置通知...")
    }
}