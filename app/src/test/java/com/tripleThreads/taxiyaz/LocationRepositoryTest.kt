package com.tripleThreads.taxiyaz

import androidx.test.InstrumentationRegistry
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.location.LocationDao
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.ArrayList

class LocationRepositoryTest {

    lateinit var locationDao: LocationDao

    lateinit var location: Location

    @Before
    fun setUp(){
        var context = InstrumentationRegistry.getTargetContext()
        locationDao = TxYzDatabase.getDatabase(context).locationDao()
        location = Location(1,"Test Location",12.0,12.0)


    }

    @Test
    fun getAllLocations() {
        locationDao.insertLocation(location)
        assertNotEquals(locationDao.getAllLocations().value,null)


    }

    @Test
    fun insert() {
        locationDao.insertLocation(location)
        assertNotEquals(locationDao.getLocationById(1).value,null)
    }
}