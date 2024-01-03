package ru.wilddisk.service

import ru.wilddisk.data.db.UsersDB
import ru.wilddisk.model.User
import ru.wilddisk.model.Users

class UsersService(private val usersDB: UsersDB) : Users {
    override suspend fun fetch(limit: Int, offset: Long): List<User.Data> = this.usersDB.fetch(limit, offset)
    override suspend fun add(user: User.Data): Unit = this.usersDB.add(user)
    override suspend fun delete(user: User.Data): Unit = this.usersDB.delete(user)
}