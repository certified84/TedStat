package com.devloyde.healthguard.networking

import com.devloyde.healthguard.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceBuilder {
    private const val baseUrl = BuildConfig.BaseUrl

    private val httpClient = OkHttpClient.Builder().addInterceptor(Interceptor {
       val request = it.request()
            .newBuilder().addHeader("Api-Key", BuildConfig.ApiKey).build()
        return@Interceptor it.proceed(request)
    })

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun<T> buildService(service: Class<T>):T{
        return retrofit.create(service)
    }


}
