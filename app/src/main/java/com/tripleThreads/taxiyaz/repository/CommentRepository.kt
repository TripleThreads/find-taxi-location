package com.tripleThreads.taxiyaz.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.APIHelpers.CommentAPI
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.comment.CommentDao
import com.tripleThreads.taxiyaz.network.CommentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CommentRepository(private val dao: CommentDao, private val commentService: CommentService?) {

     var currentComments :LiveData<List<Comment>> = dao.getAllComments(8)

    @WorkerThread
    fun getByRouteId(routeId: Long): LiveData<List<Comment>>{

        getCommentsFromAPI(routeId)
        currentComments = dao.getAllComments(routeId)

        return currentComments
    }

    @WorkerThread
    fun insert(comment: Comment): Boolean{
        insertCommentToAPI(comment)
        dao.insertComment(comment)
        return true

    }
    @WorkerThread
    fun cache(comment: Comment){
        dao.insertComment(comment)
    }

    @WorkerThread
    fun delete(comment: Comment){
        deleteCommentfromAPI(comment)
    }

    @WorkerThread
    fun update(comment: Comment){
        dao.deleteComment(comment)
        updateCommentInAPI(comment)
        getCommentsFromAPI(comment.routeId)

    }



    @WorkerThread
    fun getCommentsByRouteId(routeId: Long): List<Comment>{
        getCommentsFromAPI(routeId)
        return dao.getAllCommentByRoute(routeId)

    }
    //network functions

    private fun getCommentsFromAPI(routeId: Long){
        GlobalScope.launch(Dispatchers.IO){
            if(commentService != null){
                val response: Response<List<CommentAPI>> = commentService.getCommentsForRoute(routeId)!!.await()
                val comments = response.body()
                if(comments !=null){
                    withContext(Dispatchers.IO){
                        comments.forEach { comment ->  cache(comment.convertToComment())
                        Log.d("Comment", comment.comment)}
                    }
                }

            }
        }
    }


    private fun insertCommentToAPI(comment: Comment): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(commentService != null){
                commentService.addComment(comment)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateCommentInAPI(comment: Comment): Boolean {
        var edited= false
        GlobalScope.launch(Dispatchers.IO) {
            if(commentService != null){
                commentService.editComment(comment.id,comment)
                edited = true
            }
        }
        return edited
    }

    @WorkerThread
    fun deleteCommentfromAPI(comment: Comment): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(commentService != null){
                commentService.deleteComment(comment.id.toLong())
                deleted = true
            }
        }
        return deleted
    }
}