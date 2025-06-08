package edu.tyut.spring_boot_ssm.context

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext
import kotlin.test.Test

internal class ContextTest {

    private data class Name(
        internal val name: String,
        override val key: CoroutineContext.Key<Name> = Key
    ) : CoroutineContext.Element {
        internal companion object Key : CoroutineContext.Key<Name>
    }

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    internal fun getJob(): Unit = runTest {
        logger.info("Starting coroutine")
        logger.info("Hello1 ${Thread.currentThread()}, name1: ${coroutineContext[Name]?.name}")
        val job1: Job = launch(context = Name(name = "zsh2"), start = CoroutineStart.LAZY) {
            logger.info("Hello2 ${Thread.currentThread()}, name2: ${coroutineContext[Name]?.name}")
        }
        val job2: Job = launch(context = Name(name = "zsh3"), start = CoroutineStart.LAZY) {
            logger.info("Hello3 ${Thread.currentThread()}, name3: ${coroutineContext[Name]?.name}")
        }
        job1.start()
        job2.start()
        job1.join()
        job2.join()
    }
}