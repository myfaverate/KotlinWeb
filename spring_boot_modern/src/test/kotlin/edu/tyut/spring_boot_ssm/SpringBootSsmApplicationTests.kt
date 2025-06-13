package edu.tyut.spring_boot_ssm

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringBootSsmApplicationTests {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun contextLoads() {
        logger.info("contextLoads...")
    }

}
