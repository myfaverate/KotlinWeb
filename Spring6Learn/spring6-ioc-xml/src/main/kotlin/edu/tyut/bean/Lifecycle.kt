package edu.tyut.bean

import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class Lifecycle {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    internal fun init() {
        logger.info("init...")
    }
    internal fun destroy() {
        logger.info("destroy...")
    }
}