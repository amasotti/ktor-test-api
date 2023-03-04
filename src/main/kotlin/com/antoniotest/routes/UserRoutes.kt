/*
File: Auth
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.routes

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import com.antoniotest.service.UserService


fun Application.userRoutes(userService: UserService) {
    routing {
        route("/user") {

            get("/{name}") {
                val name = call.parameters["name"]
                if (name == null) {
                    call.respondText("Missing or malformed id")
                    return@get
                }
                val response = userService.findUserByName(name)
                if (response == null) {
                    call.respondText("No user with id $name")
                    return@get
                }
                call.respond(response.toString())
            }
        }
    }
}
