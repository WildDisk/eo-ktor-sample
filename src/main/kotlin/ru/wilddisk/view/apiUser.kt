package ru.wilddisk.view

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.get
import ru.wilddisk.data.db.DBPostgres
import ru.wilddisk.data.db.HikariConfigDefault
import ru.wilddisk.data.db.UserDB
import ru.wilddisk.data.db.UsersDB
import ru.wilddisk.model.User
import ru.wilddisk.service.UserService
import ru.wilddisk.service.UsersService

fun Route.apiUser() {
    val db = DBPostgres(
        HikariDataSource(
            HikariConfig("/hikari.properties")
        )
//        HikariConfigDefault().configure()
    )
    val userDB = UserDB.Configure(db)
    val usersDB = UsersDB.Configure(db)
    route("users") {
        get {
            call.respond(
                UsersService(usersDB)
                    .fetch(100, 0)
            )
        }
        get("{id}") {
            val id = call.parameters["id"]?.toLong() ?: -1
            call.respond(
                UserService(userDB)
                    .fetch(User.Data(id))
                    .present()
            )
        }
        post {
            val body = call.receive<User.Data>()
            UsersService(usersDB)
                .add(body)
            call.respond(HttpStatusCode.Created, "Пользователь создан!")
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toLong() ?: -1
            UsersService(usersDB)
                .delete(User.Data(id))
            call.respond(HttpStatusCode.OK, "Пользователь удалён!")
        }
    }
}