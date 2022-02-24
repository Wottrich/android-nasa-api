package github.io.wottrich.datasource.resource

import github.io.wottrich.resource.Resource
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */



object ApiResponse {

    fun <T> create(throwable: Throwable): Result<T> {
        return Result.failure(throwable)
    }

    fun <T> create(response: Response<T>): Result<T> {
        return response.bodyValidation(
            success = {
                val body = it.body()
                if (body != null && response.code() != 204) {
                    Result.success(body)
                } else {
                    Result.failure(EmptyDataException())
                }
            },
            failure = {
                Result.failure(
                    Throwable(
                        response.errorBody()?.getErrorMessage(ApiGeneralKeys.errorKey)
                    )
                )
            }
        )
    }

    private fun <T, R> Response<T>.bodyValidation(
        success: (Response<T>) -> R,
        failure: (ResponseBody?) -> R,
    ): R {
        return when {
            this.isSuccessful -> success(this)
            else -> failure(this.errorBody())
        }
    }

    private fun ResponseBody?.getErrorMessage(errorKey: String): String? {
        this?.charStream()?.readText()?.takeIf { it.contains(errorKey) }?.let {
            return JSONObject(it)[errorKey] as? String
        }
        return null
    }

}