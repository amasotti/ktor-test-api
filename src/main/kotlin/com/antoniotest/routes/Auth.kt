/*
File: Auth
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.routes

import com.antoniotest.models.users.CreateUserParams
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import com.antoniotest.service.UserService
import io.ktor.server.request.*

fun Application.authRoutes(userService: UserService) {
    routing {
        route("/auth") {

            get() {
                val name = call.parameters["name"]?.toString()
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

            post("/register") {
                val params = call.receive<CreateUserParams>()
                val response = userService.registerUser(params)
                call.respond(response)
            }
        }
    }
}
