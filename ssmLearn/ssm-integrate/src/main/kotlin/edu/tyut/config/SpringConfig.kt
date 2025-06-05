package edu.tyut.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement // 自己实现exposed的事务管理器
// @ComponentScan(basePackages = ["edu.tyut.dao", "edu.tyut.service"])
internal open class SpringConfig {

    @Bean
    internal open fun dataSource(): DataSource {
        val hikariConfig = HikariConfig("jdbc.properties")
        val dataSource = HikariDataSource(hikariConfig)
        return dataSource
    }

}