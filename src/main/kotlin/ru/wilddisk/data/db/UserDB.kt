package ru.wilddisk.data.db

import io.ktor.utils.io.errors.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.select
import ru.wilddisk.data.entity.EnUser
import ru.wilddisk.model.User

interface UserDB : User, DB {
    class Configure(private val db: DB) : UserDB {
        override fun connection(): Database = this.db.connection()
        override suspend fun fetch(user: User.Data): User.Data = this.db.query {
            EnUser.select { EnUser.id eq user.id }
                .orWhere { EnUser.username eq user.username }
                .orWhere { EnUser.email eq user.email }
                .map {
                    User.Data(
                        id = it[EnUser.id],
                        username = it[EnUser.username],
                        password = it[EnUser.password],
                        email = it[EnUser.email]
                    )
                }
                .singleOrNull() ?: throw IOException("Пользователь не найден!")
        }
    }
}