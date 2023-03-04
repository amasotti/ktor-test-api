/*
File: DatabaseFactory
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.db

import com.zaxxer.hikari.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory {

    fun init() {
        this.connect()
        transaction {
            SchemaUtils.create(UserTable)
        }
    }

    /**
     * Connect to the database
     *
     * @return Database
     */
    private fun connect(): Database {
        val hikariConfig = hikari();
        return Database.connect(hikariConfig)
    }

    /**
     * Create a Hikari connection pool
     *
     * @return HikariDataSource
     */
    private fun hikari(): HikariDataSource {
        val config = HikariConfig()

        // Set config.driverClassName to the value of the Project environment variable DATABASE_DRIVER
        config.driverClassName = System.getenv("DATABASE_DRIVER") ?: "org.postgresql.Driver"
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = System.getenv("TEST_PSQL_URL_KTOR") ?: "jdbc:postgresql://localhost:<PORT>/<DATABASE_NAME>?user=<USER>&password=<PASSWORD>"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}
    suspend fun <T> dbQuery(
        block: () -> T
    ): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }

