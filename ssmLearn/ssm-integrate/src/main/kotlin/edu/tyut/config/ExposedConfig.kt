package edu.tyut.config

import edu.tyut.entity.EmployeeEntity
import jakarta.annotation.PostConstruct
import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
internal open class ExposedConfig(
    private val dataSource: DataSource
) {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    @PostConstruct
    internal open fun connect(){
        logger.info("Connecting to DB...")
        val databaseConfig = DatabaseConfig {
            useNestedTransactions = true
        }
        Database.connect(
            datasource = dataSource,
            databaseConfig = databaseConfig,
        )
        transaction {
            logger.info("init create...")
            SchemaUtils.create(tables = arrayOf<Table>(EmployeeEntity))
        }
    }
}