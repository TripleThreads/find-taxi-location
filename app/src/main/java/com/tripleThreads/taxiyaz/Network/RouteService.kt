package com.tripleThreads.taxiyaz.Network



import com.tripleThreads.taxiyaz.data.route.Route
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RouteService {
    @GET("/api/route/all")
    fun getAllRoutes(): Deferred<Response<List<Route>>>

    @GET("/api/route")
    fun getRouteByTitle(@Query("title") title: String): Deferred<List<Route>>

    @POST("/api/route")
    fun addRoute(@Body route: Route)

    @DELETE("/api/route")
    fun deleteRoute(@Path("id") id: Long)

    @PUT("/api/route")
    fun editRoute(@Path("id") id: Long, @Body route: Route)




}