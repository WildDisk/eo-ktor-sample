package ru.wilddisk.model

import kotlinx.serialization.Serializable
import ru.wilddisk.view.View
import ru.wilddisk.view.VwUser

interface User {
    suspend fun fetch(user: User.Data): User.Data
    @Serializable
    data class Data(
        val id: Long = -1,
        val username: String = "",
        val password: String = "",
        val email: String = ""
    ) : User, View<VwUser.Data> {
        override suspend fun fetch(user: Data): Data = this
        override fun present(): VwUser.Data = VwUser.Data(this.username, this.email)
    }
}