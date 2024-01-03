package ru.wilddisk.data.db

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.wilddisk.data.entity.EnUser
import ru.wilddisk.model.User
import ru.wilddisk.model.Users

interface UsersDB : Users, DB {
    class Configure(private val db: DB) : UsersDB {
        override fun connection(): Database = this.db.connection()
        override suspend fun fetch(limit: Int, offset: Long): List<User.Data> = this.db.query {
            EnUser.selectAll()
                .orderBy(EnUser.id)
                .limit(limit, offset)
                .map {
                    User.Data(
                        id = it[EnUser.id],
                        username = it[EnUser.username],
                        password = it[EnUser.password],
                        email = it[EnUser.email]
                    )
                }
        }
        override suspend fun add(user: User.Data): Unit = this.db.query {
            EnUser.insert {
                it[username] = user.username
                it[password] = user.password
                it[email] = user.email
            }
        }
        override suspend fun delete(user: User.Data): Unit = this.db.query {
            EnUser.deleteWhere {
                (EnUser.id eq user.id) or (EnUser.username eq user.username) or
                        (EnUser.email eq user.email)
            }
        }
    }
}