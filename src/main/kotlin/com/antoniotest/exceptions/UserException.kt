/*
File: UserException
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 04.03.23
*/

package com.antoniotest.exceptions

class UserException(message: String) : Error(message) {
    companion object {
        fun userAlreadyExists(email: String): UserException {
            return UserException("User with email $email already exists")
        }
    }

    fun userNotFound(): UserException {
        return UserException("User not found")
    }
}