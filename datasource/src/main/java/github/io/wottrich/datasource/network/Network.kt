package github.io.wottrich.datasource.network

import github.io.wottrich.datasource.BuildConfig
import github.io.wottrich.datasource.adapters.RetrofitCallAdapterFactory
import java.util.concurrent.TimeUnit.MINUTES
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022F
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

object Network {

    fun buildRetrofit(
        baseUrl: String = "",
        client: OkHttpClient = defaultHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RetrofitCallAdapterFactory())
        .client(client)
        .build()

    private val defaultHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(1, MINUTES)
            builder.readTimeout(1, MINUTES)
            if (BuildConfig.DEBUG) {
                builder.addDefaultLoggingInterceptor()
            }
            return builder.build()
        }

    private fun OkHttpClient.Builder.addDefaultLoggingInterceptor(): OkHttpClient.Builder {
        return addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

}