package ru.wilddisk.view

import kotlinx.serialization.Serializable
import ru.wilddisk.model.User

interface VwUser : View<User> {
    @Serializable
    data class Data(val username: String = "", val email: String = "")
}