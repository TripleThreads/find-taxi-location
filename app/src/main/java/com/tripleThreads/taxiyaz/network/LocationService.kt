package com.tripleThreads.taxiyaz.network

import com.tripleThreads.taxiyaz.data.location.Location
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface LocationService {
    @GET("/api/node")
    fun getAllLocations(): Deferred<Response<List<Location>>>

    @POST("/api/node")
    fun addLocation(@Body location: Location): Deferred<Response<Void>>

    @PUT("/api/node/{id}")
    fun editLocation(@Path("id")id: Long, @Body location: Location):Deferred<Response<Void>>

    @DELETE("/api/node/{id}")
    fun deleteLocation(@Path("id") id: Long): Deferred<Response<Void>>
}