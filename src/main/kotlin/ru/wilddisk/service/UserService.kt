package ru.wilddisk.service

import ru.wilddisk.data.db.UserDB
import ru.wilddisk.model.User

class UserService(private val user: UserDB) : User {
    override suspend fun fetch(user: User.Data): User.Data = this.user.fetch(user)
}