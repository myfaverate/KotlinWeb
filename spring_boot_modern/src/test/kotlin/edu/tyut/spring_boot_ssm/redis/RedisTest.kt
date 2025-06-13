package edu.tyut.spring_boot_ssm.redis

import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.test.runTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.test.context.TestConstructor
import kotlin.test.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal final class RedisTest(
    private val redisTemplate: ReactiveStringRedisTemplate
) {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    internal final fun setValue(): Unit = runTest(timeout = 5.toDuration(unit = DurationUnit.SECONDS)) {
        // docker run -d --rm -p 6379:6379 --name redis docker.m.daocloud.io/library/redis
        val isSuccess: Boolean? = redisTemplate.opsForValue().setAndAwait(key = "1", value = "@", timeout = 150.seconds.toJavaDuration())
        assert(value = isSuccess == true)
        logger.info("isSuccess: $isSuccess")
    }
    @Test
    internal final fun getValue(): Unit = runTest(timeout = 5.seconds) {
        // docker run -d --rm -p 6379:6379 --name redis docker.m.daocloud.io/library/redis
        val value: String? = redisTemplate.opsForValue().getAndAwait(key = "1")
        assert(value = value == "@")
        logger.info("value: $value")
    }
}