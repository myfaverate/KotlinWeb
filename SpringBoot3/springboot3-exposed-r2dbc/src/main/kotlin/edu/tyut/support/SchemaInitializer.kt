package edu.tyut.support

import edu.tyut.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.r2dbc.SchemaUtils
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
internal final class SchemaInitializer : ApplicationRunner {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun run(args: ApplicationArguments?): Unit = runBlocking {
        suspendTransactionAsync {
            logger.info("Starting application before sourceArgs: {}, nonOptionArgs: {}, optionNames: {}, thread: {}", args?.sourceArgs, args?.nonOptionArgs, args?.optionNames,
                Thread.currentThread())
            SchemaUtils.create(tables = arrayOf<Table>(UserEntity))
            // SchemaUtils.createSchema()
            logger.info("Starting application after sourceArgs: {}, nonOptionArgs: {}, optionNames: {}, thread: {}", args?.sourceArgs, args?.nonOptionArgs, args?.optionNames,
                Thread.currentThread())
        }
    }
}