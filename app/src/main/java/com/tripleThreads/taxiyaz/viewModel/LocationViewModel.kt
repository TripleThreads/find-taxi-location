package com.tripleThreads.taxiyaz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tripleThreads.taxiyaz.Network.DataServiceGenerator
import com.tripleThreads.taxiyaz.data.node.Node
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel (application: Application): AndroidViewModel(application){
    private val repository: LocationRepository
    var allLocations: LiveData<List<Node>>


    init {
        val dao = TxYzDatabase.getDatabase(application).nodeDao()
        val locationService = application.let { DataServiceGenerator().createLocationService(it) }
        repository = LocationRepository(dao, locationService)
        allLocations =repository.allLocations
    }

    fun getLocationsRemote(){
       repository.getLocationFromAPI()
    }

    fun getLocations(){
        allLocations = repository.getAll()

    }
    fun insert(node:Node): Boolean {
        var added = false
        viewModelScope.launch(Dispatchers.IO) {
             repository.insert(node)
            added = true
        }
        return added
    }
    fun update(location: Location) = viewModelScope.launch(Dispatchers.IO){
        //TODO kasfelege
    }
    fun delete(locationId: Long) = viewModelScope.launch(Dispatchers.IO){
        //TODO
    }

    fun addAvailableNode(nodeTitle: String, destNodeTitle: String, price: Double) {


    }


}