package github.io.wottrich.datasource.adapters.resource

import github.io.wottrich.datasource.resource.ApiResponse
import github.io.wottrich.resource.Resource
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 25/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class RetrofitCallResourceAdapterResponse <R>(
    private val call: Call<R>
): Call<Resource<R>> {

    override fun enqueue(callback: Callback<Resource<R>>) {
        return call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@RetrofitCallResourceAdapterResponse,
                    Response.success(ApiResponse.create<R>(response).toResource())
                )
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                callback.onResponse(
                    this@RetrofitCallResourceAdapterResponse,
                    Response.success(ApiResponse.create<R>(t).toResource())
                )
            }

        })
    }

    private fun Result<R>.toResource(): Resource<R> {
        return when {
            this.isFailure -> Resource.failure(this.exceptionOrNull())
            this.isSuccess -> Resource.success(this.getOrNull())
            else -> Resource.failure(UnknownError())
        }
    }

    override fun clone(): Call<Resource<R>> = RetrofitCallResourceAdapterResponse(call)
    override fun isExecuted(): Boolean = call.isExecuted
    override fun cancel() = call.cancel()
    override fun isCanceled(): Boolean = call.isCanceled
    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()

    override fun execute(): Response<Resource<R>> {
        throw UnsupportedOperationException("CallAdapterResponse doesn't support execute")
    }

}