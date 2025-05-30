package edu.tyut.dating.support

import edu.tyut.dating.bean.exposed.PhotoEntity
import edu.tyut.dating.bean.exposed.UserEntity
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
internal class SchemaInitializer : ApplicationRunner {
    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun run(args: ApplicationArguments?) {
        logger.info("Starting application runner sourceArgs: {}, nonOptionArgs: {}, optionNames: {}", args?.sourceArgs, args?.nonOptionArgs, args?.optionNames)
        SchemaUtils.create(tables = arrayOf<Table>(UserEntity, PhotoEntity))
    }
}