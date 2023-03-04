package com.antoniotest.models.users

import java.time.LocalDateTime

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String? = null,
    val authToken: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)



data class CreateUserParams(
    val name: String,
    val email: String,
    val password: String
)