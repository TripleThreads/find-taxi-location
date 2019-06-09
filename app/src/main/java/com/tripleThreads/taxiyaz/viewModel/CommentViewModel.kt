package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.Network.DataServiceGenerator
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class CommentViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CommentRepository
    var comments:LiveData<List<Comment>>

    init {
        val dao = TxYzDatabase.getDatabase(application).commentDao()
        val commentService = application.let { DataServiceGenerator().createCommentService(it) }
        repository = CommentRepository(dao, commentService)
        comments = repository.currentComments

    }

    fun getComments(routeId:Int) =viewModelScope.launch(Dispatchers.IO){
        comments = repository.getByRouteId(routeId)

    }

    fun insert(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(comment)
    }

    fun update(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(comment)
    }
    fun delete(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(comment)
    }



}
