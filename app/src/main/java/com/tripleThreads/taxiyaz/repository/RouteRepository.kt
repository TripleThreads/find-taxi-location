package com.tripleThreads.taxiyaz.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.MyDao
import com.tripleThreads.taxiyaz.data.Route

class RouteRepository(private val dao: MyDao) {

    val allROutes: LiveData<List<Route>> = dao.getAllRoutes()


    @WorkerThread
    fun insert(route: Route) {
        dao.insertRoute(route)
    }

    @WorkerThread
    fun update(route: Route) {
        dao.updateRoute(route)
    }

    @WorkerThread
    fun delete(route: Route) {
        dao.deleteRoute(route)
    }

    @WorkerThread
    fun getByName(name: String): LiveData<Route> {
        var searcherDoute: LiveData<Route> = dao.getRouteByName(name)
        return searcherDoute
    }
}