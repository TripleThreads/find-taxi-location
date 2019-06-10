package com.tripleThreads.taxiyaz.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.APIHelpers.NodeConnection
import com.tripleThreads.taxiyaz.data.route.RouteDao
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.data.APIHelpers.RouteAPI
import com.tripleThreads.taxiyaz.network.RouteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RouteRepository(private val dao: RouteDao, private val routeService: RouteService?) {

    var allRoutes: LiveData<List<Route>> = dao.getAllRoutes()


    @WorkerThread
    fun insert(route: NodeConnection) {
        if(insertRouteToAPI(route)) {
            //dao.insertRoute(route)
        }
        else{
            //TODO connection error
        }
    }
    @WorkerThread
    fun cache(route: Route) {
        dao.insertRoute(route)

    }

    @WorkerThread
    fun update(route: Route) {
        if(updateRouteInAPI(route)) {
            dao.updateRoute(route)
        }
        else{
            //TODO connection error
        }
    }

    @WorkerThread
    fun delete(route: Route) {
        if(deleteRouteFromAPI(route)) {
            dao.deleteRoute(route)
        }
        else{
            //TODO connection error
        }
    }

    @WorkerThread
    fun getByName(name: String): LiveData<List<Route>> {
        Log.d("check", "In repo1")
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

                Log.d("check", "In repo2")
                    if (routeService != null) {
                        val response: Response<List<RouteAPI>> = routeService.getRouteByTitleAsync(title)!!.await()
                        val routes = response.body()
                        Log.d("check", "In repo3")

                        if (routes != null) {
                            withContext(Dispatchers.IO) {
                                //dao.deleteAll()
                                routes.forEach { route -> cache(route.convertToRoute())}
                            }
                        }
                    }
                    else{

                    }

            }
    }

    @WorkerThread
    fun insertRouteToAPI(route: NodeConnection): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(routeService != null){
                routeService.addRoute(route)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateRouteInAPI(route: Route): Boolean {
        var edited= false
        GlobalScope.launch(Dispatchers.IO) {
            if(routeService != null){
                routeService.editRoute(route.routeId.toLong(),route)
                edited = true
            }
        }
        return edited
    }

    @WorkerThread
    fun deleteRouteFromAPI(route: Route): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(routeService != null){
                routeService.deleteRoute(route.routeId.toLong())
                deleted = true
            }
        }
        return deleted
    }
}