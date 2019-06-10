package com.tripleThreads.taxiyaz.network



import com.tripleThreads.taxiyaz.data.APIHelpers.NodeConnection
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.data.APIHelpers.RouteAPI
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface RouteService {
    @GET("/api/route/all")
    fun getAllRoutes(): Deferred<Response<List<RouteAPI>>>

    @GET("/api/route")
    fun getRouteByTitleAsync(@Query("title") title: String): Deferred<Response<List<RouteAPI>>>

    @POST("/api/route")
    fun addRoute(@Body route: NodeConnection): Deferred<Response<Void>>

    @DELETE("/api/route/{id}")
    fun deleteRoute(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/route/{id}")
    fun editRoute(@Path("id") id: Long, @Body route: Route): Deferred<Response<Void>>




}