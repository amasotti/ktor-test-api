package com.antoniotest

import com.antoniotest.db.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.antoniotest.routes.authRoutes
import com.antoniotest.routes.testRoutes
import com.antoniotest.routes.userRoutes
import com.antoniotest.service.UserService
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*

fun main() {
    embeddedServer(Netty, port = 5300, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory().init()
    install(ContentNegotiation) {
        jackson()
    }
    val UserService = UserService()
    testRoutes() // Test routes: GET /antonio, GET / (Hello World!)
    authRoutes(UserService) // Auth routes: POST /auth/register
    userRoutes(UserService) // User routes: GET /user/{name}
}
