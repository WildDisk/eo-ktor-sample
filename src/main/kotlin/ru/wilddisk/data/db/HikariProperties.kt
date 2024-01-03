package ru.wilddisk.data.db

class HikariProperties(private val file: String = "/hikari1.properties") {
    override fun toString(): String = this.file
}