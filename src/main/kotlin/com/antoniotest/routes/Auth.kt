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
            /**
             * POST /auth/register
             * Register a new user
             */
            post("/register") {
                val params = call.receive<CreateUserParams>()
                val response = userService.registerUser(params)
                call.respond(response)
            }
        }
    }
}
