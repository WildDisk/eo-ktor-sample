package ru.wilddisk.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class HikariConfigDefault(
    private val url: String = "jdbc:postgresql://database:5444/eo-ktor-db",
    private val driver: String = "org.postgresql.Driver"
) {
    fun configure(): HikariDataSource = HikariDataSource(
        HikariConfig().apply {
            driverClassName = driver
            jdbcUrl = url
            username = "admin"
            password = "admin"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
    )
}