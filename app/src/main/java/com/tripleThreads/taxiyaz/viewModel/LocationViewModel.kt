package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.Network.DataServiceGenerator
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel (application: Application): AndroidViewModel(application){
    private val repository: LocationRepository
    var allLocations: LiveData<List<Location>>

    init {
        val dao = TxYzDatabase.getDatabase(application).locationDao()
        val locationService = application.let { DataServiceGenerator().createLocationService(it) }
        repository = LocationRepository(dao, locationService)
        allLocations =repository.allLocations
    }

    fun getLocations(){
        allLocations = repository.getAll()
    }

    fun insert(location: Location) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(location)
    }
    fun update(location: Location) = viewModelScope.launch(Dispatchers.IO){
        //TODO kasfelege
    }
    fun delete(locationId: Long) = viewModelScope.launch(Dispatchers.IO){
        //TODO
    }

    fun getLocationById(locationId: Long): Location? {
        return repository.getLocationById(locationId).value
    }
}