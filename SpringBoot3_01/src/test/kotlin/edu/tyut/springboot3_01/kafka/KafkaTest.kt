package edu.tyut.springboot3_01.kafka

import edu.tyut.springboot3_01.edu.tyut.bean.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.StopWatch
import java.util.concurrent.CompletableFuture
import kotlin.test.Test

@SpringBootTest
class KafkaTest(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>
){
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun sendMessage(){
        val stopWatch = StopWatch()
        stopWatch.start()
        val features: Array<CompletableFuture<SendResult<String, Any>>> = Array<CompletableFuture<SendResult<String, Any>>>(size = 1, init = { it -> kafkaTemplate.send("news", "name", "在宿舍😊 -> index: $it") })
        CompletableFuture.allOf(*features).join()
        stopWatch.stop()
        logger.info("duration: ${stopWatch.totalTimeSeconds}")
    }
    @Test
    fun senUser(){
        val feature: CompletableFuture<SendResult<String, Any>> = kafkaTemplate.send("news", "user", User(username = "John😊", password = "Doe", age = 17, salary = 100))
        val result: SendResult<String, Any> = feature.join()
        logger.info("result: $result")
    }
}