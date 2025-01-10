package com.socialize

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val a = environment.config.propertyOrNull("ktor.demo.config")?.getString()
    routing {
        get("/") {
            call.respondText("Hello World! $a")
        }
    }
}
