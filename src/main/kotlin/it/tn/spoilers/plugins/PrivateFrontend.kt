package it.tn.spoilers.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Application.configurePrivateFrontend() {
    log.info("[!] Starting Plugin - PrivateFrontend.kt")
    routing {
        get("/profile") {
            val UserSession = call.sessions.get<UserSession>()
            val UserData = call.sessions.get<UserData>()
            if (UserSession != null) {
                call.respond(
                    MustacheContent(
                        "profile.hbs", mapOf(
                            "user" to user(
                                name = UserData?.givenName,
                                surname = UserData?.familyName,
                                photo = UserData?.picture,
                                id = UserData?.sub,
                                email = UserData?.email,
                                realm = UserData?.hd,
                                gsuite = UserData?.GSuiteUser
                            ), "logged" to (call.sessions.get<UserData>() != null)
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Not authenticated")
            }
        }
        get("/settings") {
            val UserData = call.sessions.get<UserData>()
            val UserSession = call.sessions.get<UserSession>()
            if (UserSession != null && UserData != null) {
                call.respond(
                    MustacheContent(
                        "settings.hbs", mapOf(
                            "user" to user(
                                name = UserData.givenName,
                                surname = UserData.familyName,
                                photo = UserData.picture,
                                id = UserData.sub,
                                email = UserData.email,
                                realm = UserData.hd,
                                gsuite = UserData.GSuiteUser
                            ), "logged" to (call.sessions.get<UserData>() != null)
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Not authenticated")
            }
        }
        get("/dashboard") {
            val UserData = call.sessions.get<UserData>()
            val UserSession = call.sessions.get<UserSession>()
            if (UserSession != null && UserData != null) {
                call.respond(
                    MustacheContent(
                        "dashboard.hbs", mapOf(
                            "user" to user(
                                name = UserData.givenName,
                                surname = UserData.familyName,
                                photo = UserData.picture,
                                id = UserData.sub,
                                email = UserData.email,
                                realm = UserData.hd,
                                gsuite = UserData?.GSuiteUser
                            ), "logged" to (call.sessions.get<UserData>() != null)
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Not authenticated")
            }
        }
        get("/NewInsertion") {
            val UserData = call.sessions.get<UserData>()
            val UserSession = call.sessions.get<UserSession>()
            if (UserSession != null && UserData != null) {
                call.respond(
                    MustacheContent(
                        "newInsertion.hbs", mapOf(
                            "user" to user(
                                name = UserData.givenName,
                                surname = UserData.familyName,
                                photo = UserData.picture,
                                id = UserData.sub,
                                email = UserData.email,
                                realm = UserData.hd,
                                gsuite = UserData?.GSuiteUser
                            ), "logged" to (call.sessions.get<UserData>() != null)
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Not authenticated")
            }
        }
        get("/messaggi") {
            val UserData = call.sessions.get<UserData>()
            val UserSession = call.sessions.get<UserSession>()
            if (UserSession != null && UserData != null) {
                call.respond(
                    MustacheContent(
                        "messages.hbs", mapOf(
                            "user" to user(
                                name = UserData.givenName,
                                surname = UserData.familyName,
                                photo = UserData.picture,
                                id = UserData.sub,
                                email = UserData.email,
                                realm = UserData.hd,
                                gsuite = UserData?.GSuiteUser
                            ), "logged" to (call.sessions.get<UserData>() != null)
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Not authenticated")
            }
        }
    }
}