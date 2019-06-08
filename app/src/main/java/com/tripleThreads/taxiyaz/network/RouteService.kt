package com.tripleThreads.taxiyaz.network



import com.tripleThreads.taxiyaz.data.route.Route
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface RouteService {
    @GET("/api/route/all")
    fun getAllRoutesAsync(): Deferred<Response<List<Route>>>

    @GET("/api/route")
    fun getRouteByTitleAsync(@Query("title") title: String): Deferred<Response<List<Route>>>

    @POST("/api/route")
    fun addRouteAsync(@Body route: Route)

    @DELETE("/api/route")
    fun deleteRouteAsync(@Path("id") id: Long)

    @PUT("/api/route")
    fun editRouteAsync(@Path("id") id: Long, @Body route: Route)




}