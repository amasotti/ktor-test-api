/*
File: UserTable
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users") {
    val id = integer("id" ).autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 50 )
    val password = text("password" )
    val authToken = text("auth_token" ).nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(id)

}
