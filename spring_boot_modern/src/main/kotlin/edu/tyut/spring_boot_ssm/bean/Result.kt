package edu.tyut.spring_boot_ssm.bean

@ConsistentCopyVisibility
internal data class Result <T> private constructor(
    internal val code: Int,
    internal val message: String,
    internal val data: T,
) {
    internal companion object {
        private const val SUCCESS: Int = 200
        private const val FAILURE: Int = 500
        internal fun <T> success(message: String, data: T) = Result<T>(code = SUCCESS, message = message, data = data)
        internal fun <T> failure(message: String, data: T) = Result<T>(code = FAILURE, message = message, data = data)
    }
}
