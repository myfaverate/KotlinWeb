package edu.tyut.dating.bean


internal sealed class Result {

    internal data class Success<T>(
        internal val code: Int,
        internal val message: String,
        internal val data: T
    ) : Result()

    internal data class Failure<T>(
        internal val code: Int,
        internal val message: String,
        internal val data: T
    ) : Result()

    internal companion object {
        private const val SUCCESS: Int = 200
        private const val FAILURE: Int = 500
        internal fun <T> success(message: String, data: T): Success<T> {
            return Success(code = SUCCESS, message = message, data = data)
        }

        internal fun <T> failure(message: String, data: T): Failure<T> {
            return Failure(code = FAILURE, message = message, data = data)
        }
    }
}
