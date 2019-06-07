package com.tripleThreads.taxiyaz.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.route.RouteDao
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.network.RouteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RouteRepository(private val dao: RouteDao,val routeService: RouteService?) {

    var allRoutes: LiveData<List<Route>> = dao.getAllRoutes()


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
        getRouteFromAPI(name)
        allRoutes = dao.getAllRoutes()
        return dao.getRouteByName(name)
    }

    @WorkerThread
    fun delete() {
        dao.deleteAll()
    }

    //network functions

    @WorkerThread
    fun getRouteFromAPI(title: String){
            GlobalScope.launch(Dispatchers.IO) {

                    if (routeService != null) {
                        val response: Response<List<Route>> = routeService.getAllRoutes()!!.await()
                        val routes = response.body()

                        if (routes != null) {
                            withContext(Dispatchers.IO) {
                                routes.forEach { route -> insert(route)}
                            }
                        }
                    }
                    else{

                    }

            }



    }
}