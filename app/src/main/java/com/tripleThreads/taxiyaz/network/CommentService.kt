package com.tripleThreads.taxiyaz.network

import com.tripleThreads.taxiyaz.data.APIHelpers.CommentAPI
import com.tripleThreads.taxiyaz.data.comment.Comment
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface CommentService {
    @GET("api/comment/{id}")
    fun getCommentsForRoute(@Path("id") routeId: Long): Deferred<Response<List<CommentAPI>>>

    @POST("/api/comment")
    fun addComment(@Body comment: Comment): Deferred<Response<Void>>

    @DELETE("/api/comment/{id}")
    fun deleteComment(@Path("id")id: Long): Deferred<Response<Void>>

    @PUT("/api/comment/{id}")
    fun editComment(@Path("id")id: Long, @Body comment: Comment): Deferred<Response<Void>>
}