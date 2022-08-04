package it.tn.spoilers.plugins

import io.ktor.server.plugins.compression.*
import io.ktor.server.application.*

fun Application.configureCompression() {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
}