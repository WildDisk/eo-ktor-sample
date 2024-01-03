package ru.wilddisk.model

interface Users {
    suspend fun fetch(limit: Int, offset: Long): List<User.Data>
    suspend fun add(user: User.Data)
    suspend fun delete(user: User.Data)
}