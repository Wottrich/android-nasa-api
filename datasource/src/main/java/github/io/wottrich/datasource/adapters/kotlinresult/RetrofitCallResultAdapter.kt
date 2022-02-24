package github.io.wottrich.datasource.adapters.kotlinresult

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RetrofitCallResultAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<Result<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<Result<R>> {
        return RetrofitCallResultAdapterResponse(call)
    }
}