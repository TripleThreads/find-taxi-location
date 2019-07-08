package com.tripleThreads.taxiyaz.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.newRoute.APIRoute
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.data.newRoute.RouteDao
import com.tripleThreads.taxiyaz.network.RouteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class RouteRepository(private val dao: RouteDao, private val routeService: RouteService?) {
    var allRoutes: LiveData<List<Route>> = dao.getAllRoutes()

    @WorkerThread
    fun cache(route: Route) {
        dao.insertRoute(route)
    }

    @WorkerThread
    fun getRouteFromAPI(title: String){
        GlobalScope.launch(Dispatchers.IO) {

            Log.d("check", "In repo2")
            if (routeService != null) {
                val response: Response<List<APIRoute>> = routeService.getRouteByTitleAsync(title)!!.await()
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

    fun getByName(name: String): LiveData<List<Route>> {
        Log.d("check", "In repo1")
        getRouteFromAPI(name)
        allRoutes = dao.getAllRoutes()
        return dao.getRouteByName("%$name%")
    }

    fun getAll(): LiveData<List<Route>> {
        return dao.getAllRoutes()
    }


}