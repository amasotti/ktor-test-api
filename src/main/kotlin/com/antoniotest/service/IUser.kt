package com.antoniotest.service

import com.antoniotest.models.users.*
import com.antoniotest.utils.BaseResponse


interface IUser {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun findUserByEmail(email: String): BaseResponse<Any>? = null
}