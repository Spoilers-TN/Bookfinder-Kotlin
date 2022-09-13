package it.tn.spoilers.plugins.frontend

import com.mitchellbosecke.pebble.loader.ClasspathLoader
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.pebble.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import it.tn.spoilers.data.*
import it.tn.spoilers.database.models.Users
import it.tn.spoilers.database.models.UsersData
import it.tn.spoilers.database.services.BooksService
import it.tn.spoilers.database.services.ReviewsService
import it.tn.spoilers.database.services.UsersService

fun Application.configurePublicFrontend() {
    log.info("[!] Starting Plugin - PublicFrontend.kt")
    install(Pebble) {
        loader(ClasspathLoader().apply {
            prefix = "templates"
        })
    }
    routing {
        get("/") {
            val UserData = call.sessions.get<UsersData>()
            call.respond(
                PebbleContent(
                    "index.html", mapOf(
                        "user" to user(
                            name = UserData?.User_Name,
                            uuid = UserData?.User_UUID,
                            surname = UserData?.User_Surname,
                            photo = UserData?.User_Photo,
                            id = UserData?.User_ID,
                            email = UserData?.User_Email,
                            realm = UserData?.User_School_Domain,
                            gsuite = UserData?.User_GSuite
                        ), "logged" to (call.sessions.get<UsersData>() != null)
                    )
                )
            )
        }
        get("/index") {
            call.respondRedirect("/")
        }
        get("/about") {
            val UserData = call.sessions.get<UsersData>()
            call.respond(
                PebbleContent(
                    "about.html", mapOf(
                        "user" to user(
                            name = UserData?.User_Name,
                            uuid = UserData?.User_UUID,
                            surname = UserData?.User_Surname,
                            photo = UserData?.User_Photo,
                            id = UserData?.User_ID,
                            email = UserData?.User_Email,
                            realm = UserData?.User_School_Domain,
                            gsuite = UserData?.User_GSuite
                        ), "logged" to (call.sessions.get<UsersData>() != null)
                    )
                )
            )
        }
        get("/search") {
            val UserData = call.sessions.get<UsersData>()
            with(call) {
                respond(
                    PebbleContent(
                        "search.html", mapOf(
                            "books" to BooksService().findAll(),
                            "user" to user(
                                name = UserData?.User_Name,
                                uuid = UserData?.User_UUID,
                                surname = UserData?.User_Surname,
                                photo = UserData?.User_Photo,
                                id = UserData?.User_ID,
                                email = UserData?.User_Email,
                                realm = UserData?.User_School_Domain,
                                gsuite = UserData?.User_GSuite
                            ),
                            "logged" to (sessions.get<UsersData>() != null)
                        )
                    )
                )
            }
        }
        get("/search/{id}") {
            val UserData = call.sessions.get<UsersData>()
            with(call) {
                respond(
                    PebbleContent(
                        "search.html", mapOf(
                            "books" to BooksService().findByISBN(call.parameters["isbn"]!!.toLong()),
                            "user" to user(
                                name = UserData?.User_Name,
                                uuid = UserData?.User_UUID,
                                surname = UserData?.User_Surname,
                                photo = UserData?.User_Photo,
                                id = UserData?.User_ID,
                                email = UserData?.User_Email,
                                realm = UserData?.User_School_Domain,
                                gsuite = UserData?.User_GSuite
                            ),
                            "logged" to (sessions.get<UsersData>() != null)
                        )
                    )
                )
            }
        }
        get("/terms") {
            val UserData = call.sessions.get<UsersData>()
            call.respond(
                PebbleContent(
                    "terms.html", mapOf(
                        "user" to user(
                            name = UserData?.User_Name,
                            uuid = UserData?.User_UUID,
                            surname = UserData?.User_Surname,
                            photo = UserData?.User_Photo,
                            id = UserData?.User_ID,
                            email = UserData?.User_Email,
                            realm = UserData?.User_School_Domain,
                            gsuite = UserData?.User_GSuite
                        ), "logged" to (call.sessions.get<UsersData>() != null)
                    )
                )
            )
        }
        get("/who") {
            val UserData = call.sessions.get<UsersData>()
            call.respond(
                PebbleContent(
                    "who.html", mapOf(
                        "user" to user(
                            name = UserData?.User_Name,
                            uuid = UserData?.User_UUID,
                            surname = UserData?.User_Surname,
                            photo = UserData?.User_Photo,
                            id = UserData?.User_ID,
                            email = UserData?.User_Email,
                            realm = UserData?.User_School_Domain,
                            gsuite = UserData?.User_GSuite
                        ), "logged" to (call.sessions.get<UsersData>() != null)
                    )
                )
            )
        }
        get("/policy") {
            val UserData = call.sessions.get<UsersData>()
            call.respond(
                PebbleContent(
                    "policy.html", mapOf(
                        "user" to user(
                            name = UserData?.User_Name,
                            uuid = UserData?.User_UUID,
                            surname = UserData?.User_Surname,
                            photo = UserData?.User_Photo,
                            id = UserData?.User_ID,
                            email = UserData?.User_Email,
                            realm = UserData?.User_School_Domain,
                            gsuite = UserData?.User_GSuite
                        ), "logged" to (call.sessions.get<UsersData>() != null)
                    )
                )
            )
        }
    }
    log.info("[✓] Started Plugin - PublicFrontend.kt")
}