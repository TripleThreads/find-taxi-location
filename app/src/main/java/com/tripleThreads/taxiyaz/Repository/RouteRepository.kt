package com.tripleThreads.taxiyaz.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.route.RouteDao
import com.tripleThreads.taxiyaz.data.route.Route

class RouteRepository(private val dao: RouteDao) {

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

    @WorkerThread
    fun delete() {
        dao.deleteAll()
    }
}