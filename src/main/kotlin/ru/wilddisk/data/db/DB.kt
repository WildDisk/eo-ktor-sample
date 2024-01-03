package ru.wilddisk.data.db

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface DB {
    fun connection(): Database
}

suspend fun <T> DB.query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO, this.connection()) {
    block()
}