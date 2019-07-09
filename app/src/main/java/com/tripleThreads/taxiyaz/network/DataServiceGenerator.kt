package com.tripleThreads.taxiyaz.Network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tripleThreads.taxiyaz.BuildConfig
import com.tripleThreads.taxiyaz.network.CommentService
import com.tripleThreads.taxiyaz.network.NodeService
import com.tripleThreads.taxiyaz.network.RouteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataServiceGenerator {

    private val baseUrl = "http://10.42.0.1:8080/"

    fun createRouteService(context: Context): RouteService? {
        val connected = checkInternet(context)

        if(connected != null && connected ) {
            val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(baseUrl)
            val httpClient = OkHttpClient.Builder()
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

    fun createCommentService(application: Application): CommentService? {

        val connected = checkInternet(application)

        if(connected != null && connected ) {
            try {
                val builder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl(baseUrl)
                val httpClient = OkHttpClient.Builder()
                    .cache(null)
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                    httpClient.addInterceptor(interceptor)
                }
                builder.client(httpClient.build())
                val retrofit = builder.build()
                return retrofit.create(CommentService::class.java)
            }
            catch (e: Exception){}
        }

        return null

    }

    fun createLocationService(application: Application): NodeService? {
        val connected = checkInternet(application)

        if(connected != null && connected ) {
            try {
                val builder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl(baseUrl)
                val httpClient = OkHttpClient.Builder()
                    .cache(null)
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                    httpClient.addInterceptor(interceptor)
                }
                builder.client(httpClient.build())
                val retrofit = builder.build()
                return retrofit.create(NodeService::class.java)
            }
            catch (e: Exception){}
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