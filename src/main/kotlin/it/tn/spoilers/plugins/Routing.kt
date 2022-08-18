package it.tn.spoilers.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*

fun Application.configureRouting() {
    log.info("[!] Starting Plugin - Routing.kt")
    install(AutoHeadResponse)

    install(Webjars) {
        path = "/webjars" //defaults to /webjars
    }

    routing {
        // Static plugin. Try to access `/static/index.html`
        static("/assets") {
            resources("assets")
        }
        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }
        get("/teapot") {
            call.respondText(text = "418: I'm a teapot \uD83E\uDED6", status = HttpStatusCode.fromValue(418))
        }
    }
    log.info("[✓] Started Plugin - Routing.kt")
}
