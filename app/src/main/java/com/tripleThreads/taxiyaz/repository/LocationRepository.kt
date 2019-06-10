package com.tripleThreads.taxiyaz.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.location.LocationDao
import com.tripleThreads.taxiyaz.network.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LocationRepository(private val dao: LocationDao, private val locationService: LocationService?) {
    var allLocations: LiveData<List<Location>> = dao.getAllLocations()

    @WorkerThread
    fun getAll(): LiveData<List<Location>>{
        getLocationFromAPI()
        allLocations = dao.getAllLocations()
        return allLocations

    }
    @WorkerThread
    private fun cache(location: Location) {
        dao.insertLocation(location)
    }

    @WorkerThread
    fun insert(location: Location):Boolean{
        return if (insertLocationToAPI(location)){
            dao.insertLocation(location)
            true
        } else{
            false
        }

    }


    //network functions
    @WorkerThread
    fun getLocationFromAPI(){
        GlobalScope.launch(Dispatchers.IO){
            if (locationService != null){
                val response: Response<List<Location>> = locationService.getAllLocations()!!.await()
                val locations = response.body()
                if(locations != null){
                    withContext(Dispatchers.IO){
                        locations.forEach { location -> cache(location) }
                    }
                }
            }
        }
    }

    @WorkerThread
    fun insertLocationToAPI(location: Location): Boolean{
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(locationService != null){
                locationService.addLocation(location)
                added = true
            }
        }
        return added
    }

    fun getLocationById(locationId: Long): LiveData<Location> {
        return dao.getLocationById(locationId)
    }


}