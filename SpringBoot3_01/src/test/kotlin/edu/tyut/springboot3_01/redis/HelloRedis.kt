package edu.tyut.springboot3_01.redis

import edu.tyut.springboot3_01.edu.tyut.bean.User
import io.lettuce.core.KillArgs.Builder.user
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import kotlin.test.Test

@SpringBootTest
class HelloRedis(
    @Autowired
    private val stringRedisTemplate: StringRedisTemplate,
    @Autowired
    private val redisTemplate: RedisTemplate<Any, Any>,
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun opsRedis(){
        val value: String? = stringRedisTemplate.opsForValue().get("count")
        logger.info("Value is $value")
        val rows: Long? = stringRedisTemplate.opsForList().rightPushAll("list", List(10){ "Content😊，索引: $it" })
        logger.info("Rows is $rows")
        logger.info("list is ${stringRedisTemplate.opsForList().range("list", 0, 10)}")
    }
    @Test
    fun saveUser(){
        redisTemplate.opsForValue().set(/* key = */ "user", /* value = */ User("张书豪😊", "123456", 19, 8000))
        val user: User? = redisTemplate.opsForValue().get("user") as? User
        logger.info("User is $user")
    }
}