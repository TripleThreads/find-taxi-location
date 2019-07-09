package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var name: String = ""

    private val repository: UserRepository
    var user: User? = null

    init {
        val userDao = TxYzDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getUser()
    }

    fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        user = repository.getUser()
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }
    fun update(user: User) = viewModelScope.launch (Dispatchers.IO){
        repository.update(user)
    }
    fun delete(user: User) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(user)
    }

}