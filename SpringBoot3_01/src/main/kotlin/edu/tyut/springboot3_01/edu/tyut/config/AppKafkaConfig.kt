package edu.tyut.springboot3_01.edu.tyut.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AppKafkaConfig {
    @Bean
    fun thingTopic(): NewTopic {
        return TopicBuilder.name("thing").partitions(1).replicas(1).compact().build()
    }
}