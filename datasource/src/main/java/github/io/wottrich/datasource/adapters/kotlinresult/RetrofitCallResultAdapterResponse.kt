package github.io.wottrich.datasource.adapters.kotlinresult

import github.io.wottrich.datasource.resource.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallResultAdapterResponse<R> (
    private val call: Call<R>
): Call<Result<R>> {

    override fun enqueue(callback: Callback<Result<R>>) {
        return call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@RetrofitCallResultAdapterResponse,
                    Response.success(ApiResponse.create(response))
                )
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                callback.onResponse(
                    this@RetrofitCallResultAdapterResponse,
                    Response.success(ApiResponse.create(t))
                )
            }

        })
    }

    override fun clone(): Call<Result<R>> = RetrofitCallResultAdapterResponse(call)
    override fun isExecuted(): Boolean = call.isExecuted
    override fun cancel() = call.cancel()
    override fun isCanceled(): Boolean = call.isCanceled
    override fun request(): Request = call.request()
    override fun timeout(): Timeout = call.timeout()

    override fun execute(): Response<Result<R>> {
        throw UnsupportedOperationException("CallAdapterResponse doesn't support execute")
    }

}