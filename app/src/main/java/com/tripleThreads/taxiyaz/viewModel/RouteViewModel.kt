package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.Network.DataServiceGenerator
import com.tripleThreads.taxiyaz.data.APIHelpers.NodeConnection
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.repository.RouteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RouteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RouteRepository
    var allRoutes: LiveData<List<Route>>
    lateinit var searchedRoute: LiveData<List<Route>>
    lateinit var route: Route

    init {
        val dao = TxYzDatabase.getDatabase(application).routeDao()
        val routeService = application.let { DataServiceGenerator().createRouteService(it)}
        repository = RouteRepository(dao, routeService)
        allRoutes = repository.allRoutes

    }

    fun getRoutes(name: String): LiveData<List<Route>> {
        Log.d("check", "In ViewModel")
        allRoutes = repository.getByName(name)
        return allRoutes
    }

    fun update(route: Route) = viewModelScope.launch (Dispatchers.IO){
        //repository.update(route)
    }

    fun delete(route: Route) = viewModelScope.launch (Dispatchers.IO){
        //repository.delete(route)
    }

    fun getRouteByName(name:String) = viewModelScope.launch (Dispatchers.IO){
        searchedRoute = repository.getByName(name)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        //repository.delete()
    }

    fun getAll() = viewModelScope.launch(Dispatchers.IO) {
        allRoutes = repository.getAll()
    }
    fun rateRoute(id:Long, rating: Float) = viewModelScope.launch(Dispatchers.IO) {
        repository.rateRoute(id,rating)
    }

    fun addToBookMark(id: Long) = viewModelScope.launch(Dispatchers.IO){
        val route = repository.getRouteById(id)
        route.bookmarked = !route.bookmarked
        //repository.updateLocal(route)
    }
    fun getBookMarked() = viewModelScope.launch(Dispatchers.IO){
        allRoutes = repository.getBookMarked()
    }

    fun getRoute(id: Long) = viewModelScope.launch (Dispatchers.IO){
        route = repository.getRouteById(id)
    }
}