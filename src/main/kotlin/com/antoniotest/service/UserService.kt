/*
File: UserService
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.service

import com.antoniotest.db.UserTable
import com.antoniotest.db.dbQuery
import com.antoniotest.exceptions.UserException
import com.antoniotest.models.users.*
import com.antoniotest.utils.BaseResponse
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserService : IUser {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        if (findUserByEmail(params.email) != null) {
            return BaseResponse.ClientError("User already exists")
        }

        val response = insertUser(params)
        return BaseResponse.Success(response)

    }

    suspend fun findUserByName(name: String): BaseResponse<Any>? {
        var user: User? = null
        dbQuery {
            user = UserTable.select { UserTable.name eq name }.mapNotNull { rowToUser(it) }.singleOrNull()
        }
        return user?.let { BaseResponse.Success(it) }
    }

    private suspend fun insertUser(params: CreateUserParams): User {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[name] = params.name
                it[email] = params.email
                it[password] = params.password
            }
        }
        return statement?.resultedValues?.get(0)?.let { rowToUser(it) } ?: throw UserException("User not found")
    }

    private fun rowToUser(row: ResultRow?): User {
        when (row) {
            null -> throw UserException("User not found")
            else -> return User(
                id = row[UserTable.id],
                name = row[UserTable.name],
                email = row[UserTable.email],
                password = row[UserTable.password],
                authToken = row[UserTable.authToken],
                createdAt = row[UserTable.createdAt],
                updatedAt = row[UserTable.updatedAt]
            )
        }
    }
    @Throws(UserException::class)
    override suspend fun findUserByEmail(email: String): BaseResponse<Any>? {
        return null
    }
}