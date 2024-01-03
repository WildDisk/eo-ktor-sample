package ru.wilddisk.data.db

import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DBPostgres(private val connect: Database) : DB {
    constructor(hikari: HikariDataSource) : this(connect = Database.connect(hikari))
    override fun connection(): Database = this.connect
}