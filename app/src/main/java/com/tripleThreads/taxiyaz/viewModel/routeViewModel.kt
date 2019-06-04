package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.repository.RouteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class routeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RouteRepository
    val allRoutes: LiveData<List<Route>>
    lateinit var searchedroute: LiveData<Route>

    init {
        val dao = TxYzDatabase.getDatabase(application).routeDao()
        repository = RouteRepository(dao)
        allRoutes = repository.allROutes

    }

    fun insert(route: Route) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(route)
    }
    fun update(route: Route) = viewModelScope.launch (Dispatchers.IO){
        repository.update(route)
    }
    fun delete(route: Route) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(route)
    }
    fun getRouteByName(name:String) = viewModelScope.launch (Dispatchers.IO){
        searchedroute = repository.getByName(name)
    }
}