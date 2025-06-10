package edu.tyut.spring_boot_ssm.validation

import edu.tyut.spring_boot_ssm.annotation.State
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

internal class StateValidation internal constructor() : ConstraintValidator<State, String> {
    override fun isValid(
        value: String?,
        context: ConstraintValidatorContext?
    ): Boolean {
        return "Draft" == value || "Publish" == value
    }
}