package it.tn.spoilers.plugins.serving

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*

/**
 * Class for configuring the compression
 *
 * @author Francesco Masala
 * @since Bookfinder - 2022.8.18
 */
fun Application.configureCompression() {
    log.info("[!] Starting Plugin - Compression.kt")
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
    log.info("[✓] Started Plugin - Compression.kt")
}