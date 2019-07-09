package com.tripleThreads.taxiyaz

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.node.Node
import com.tripleThreads.taxiyaz.data.node.NodeDao
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LocationRepositoryTest {

    lateinit var locationDao: NodeDao

    lateinit var location: Node

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        locationDao = TxYzDatabase.getDatabase(context).nodeDao()
        location = Node(1,"Test Location",12.0,12.0)


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