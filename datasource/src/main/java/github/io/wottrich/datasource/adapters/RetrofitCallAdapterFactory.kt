package github.io.wottrich.datasource.adapters

import github.io.wottrich.datasource.adapters.kotlinresult.RetrofitCallResultAdapter
import github.io.wottrich.datasource.adapters.resource.RetrofitCallResourceAdapter
import github.io.wottrich.resource.Resource
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 14/08/20
 *
 * Copyright © 2020 GithubProfile. All rights reserved.
 *
 */

class RetrofitCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) == Call::class.java) {
            val enclosingType = getParameterUpperBound(0, returnType as ParameterizedType)
            val rawType = getRawType(enclosingType)

            if (rawType != Resource::class.java && rawType != Result::class.java) {
                throw IllegalArgumentException("type must be a ApiResponse")
            }

            if (enclosingType !is ParameterizedType) {
                throw IllegalArgumentException("resource must be parameterized")
            }

            val bodyType = getParameterUpperBound(0, enclosingType)
            return getCallAdapter(rawType, bodyType)
        }

        return null
    }

    private fun getCallAdapter(rawType: Class<*>, bodyType: Type): CallAdapter<*, *>? {
        return when (rawType) {
            Resource::class.java -> RetrofitCallResourceAdapter<Any>(bodyType)
            Result::class.java -> RetrofitCallResultAdapter<Any>(bodyType)
            else -> null
        }
    }

}