package edu.tyut.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

internal object Constants {
    private val logger: Logger = LoggerFactory.getLogger(Constants::class.java)
    internal val scope = CoroutineScope(
        context = CoroutineExceptionHandler { _: CoroutineContext, throwable: Throwable ->
            logger.error(throwable.message, throwable)
        } + SupervisorJob() + Executors.newVirtualThreadPerTaskExecutor().apply {
            Runtime.getRuntime().addShutdownHook(Thread {
                logger.info("Shutting down coroutine handler...")
                shutdown()
            })
        }
        .asCoroutineDispatcher()
    )
}