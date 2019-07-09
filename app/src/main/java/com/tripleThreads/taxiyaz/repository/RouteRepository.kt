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
                val response: Response<List<APIRoute>> = routeService.getRouteByTitleAsync(title).await()
                val routes = response.body()
                Log.d("check", "In repo3")

                if (routes != null) {
                    withContext(Dispatchers.IO) {
                        //dao.deleteAll()
                        routes.forEach { route ->
                            val b = dao.getRouteById(route.routeId)
                            var bk = false
                            if (b != null) {
                                bk = b.bookmarked
                            }
                            cache(route.convertToRoute(bk))
                        }
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
    fun updateLocal(id:Long, rate: Float){
        var route:Route = dao.getRouteById(id)
        route.rating = rate
        dao.updateRoute(route)
    }

    fun getAll(): LiveData<List<Route>> {
        return dao.getAllRoutes()
    }
    fun rateRoute(id:Long, rate: Float){
        GlobalScope.launch(Dispatchers.IO) {
            if (routeService != null) {
                val response: Response<Void> = routeService.addRating(id,rate)!!.await()
                if(response.isSuccessful){
                    updateLocal(id, rate)
                }

            }
        }
    }

    fun getRouteById(id: Long): Route {
        return dao.getRouteById(id)
    }

    fun getBookMarked(): LiveData<List<Route>> {
        return  dao.getBookMarked()
    }


}