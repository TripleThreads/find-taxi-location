package com.tripleThreads.taxiyaz.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tripleThreads.taxiyaz.BuildConfig
import com.tripleThreads.taxiyaz.network.RouteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataServiceGenerator {

    fun createRouteService(context: Context): RouteService? {
        val connected = checkInternet(context)

        if(connected != null && connected ) {
            val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("http://192.168.1.2:8080/")
            val httpClient = OkHttpClient.Builder()
//            .readTimeout(90,TimeUnit.SECONDS)
//            .connectTimeout(90,TimeUnit.SECONDS)
//            .writeTimeout(90,TimeUnit.SECONDS)
                .cache(null)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(interceptor)
            }
            builder.client(httpClient.build())
            val retrofit = builder.build()
            return retrofit.create(RouteService::class.java)
        }
        return null

    }
    companion object{
        fun checkInternet(context: Context): Boolean? {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? =connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected
        }
    }
}