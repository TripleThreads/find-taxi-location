package com.tripleThreads.taxiyaz.network

import com.tripleThreads.taxiyaz.data.node.Node
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NodeService {
    @GET("/api/node")
    fun getAllLocations(): Deferred<Response<List<Node>>>

    @POST("/api/node")
    fun addLocation(@Body node: Node): Deferred<Response<Void>>

    @PUT("/api/node/{id}")
    fun editLocation(@Path("id")id: Long, @Body node: Node):Deferred<Response<Void>>

    @DELETE("/api/node/{id}")
    fun deleteLocation(@Path("id") id: Long): Deferred<Response<Void>>
}