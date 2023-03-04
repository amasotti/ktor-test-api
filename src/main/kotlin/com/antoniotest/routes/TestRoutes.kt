package com.antoniotest.routes

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.testRoutes() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get ("/antonio" ) {
            call.respondText("Hello World Antonio!")
        }
    }
}
