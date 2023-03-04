package com.antoniotest.utils


import io.ktor.http.*

sealed class BaseResponse<T>(
    val status: HttpStatusCode,
    val message: String,
) {
    data class Success<T>(val data: T? = null) :
        BaseResponse<T>(status = HttpStatusCode.OK, message = "Success")

    data class ClientError<T>(val data: T? = null) :
        BaseResponse<T>(status = HttpStatusCode.BadRequest, message = "Error")
}