package edu.tyut.springboot3_01.edu.tyut.listener

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.stereotype.Component

@Component
class ThingKafkaTopicListener {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @KafkaListener(topics = ["thing"], groupId = "thing")
    fun thingTopic(record: ConsumerRecord<String, String>) {
        val topic: String = record.topic()
        val key: String = record.key()
        val value: String = record.value()
        logger.info("record: {}, Topic: {}, key: {}, value: {}", record, topic, key, value)
    }
    @KafkaListener(topics = ["news"], groupId = "news", topicPartitions = [
        TopicPartition(topic = "news", partitionOffsets = [ PartitionOffset(partition = "0", initialOffset = "9999") ])
    ])
    fun newsTopic(record: ConsumerRecord<String, String>) {
        val topic: String = record.topic()
        val key: String = record.key()
        val value: String = record.value()
        logger.info("newsTopic record: {}, Topic: {}, key: {}, value: {}", record, topic, key, value)
    }
}