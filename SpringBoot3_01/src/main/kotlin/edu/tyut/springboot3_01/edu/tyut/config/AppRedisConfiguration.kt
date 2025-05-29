package edu.tyut.springboot3_01.edu.tyut.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class AppRedisConfiguration {
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val redisTemplate: RedisTemplate<Any, Any> = RedisTemplate<Any, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        return redisTemplate
    }
}