package ru.wilddisk.data.entity

import org.jetbrains.exposed.sql.Table

object EnUser : Table("users") {
    val id = long("id").autoIncrement()
    val username = varchar("username", 50).default("")
    val password = varchar("password", 50).default("")
    val email = varchar("email", 100).default("")
    override val primaryKey = PrimaryKey(id)
}