package edu.tyut.spring_boot_ssm.annotation

import edu.tyut.spring_boot_ssm.validation.StateValidation
import jakarta.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.FIELD])
@Retention(value = AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [StateValidation::class])
internal annotation class State(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)