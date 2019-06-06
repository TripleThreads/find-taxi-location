package com.tripleThreads.taxiyaz.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tripleThreads.taxiyaz.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataServiceGenerator {

    fun createRouteService(): RouteService? {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http:google.com/")
        val httpClient =  OkHttpClient.Builder()
//            .readTimeout(90,TimeUnit.SECONDS)
//            .connectTimeout(90,TimeUnit.SECONDS)
//            .writeTimeout(90,TimeUnit.SECONDS)
            .cache(null)
        if (BuildConfig.DEBUG){
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(interceptor)
        }
        builder.client(httpClient.build())
        val retrofit = builder.build()
        return retrofit.create(RouteService::class.java)

    }
}