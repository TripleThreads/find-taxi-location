package com.tripleThreads.taxiyaz

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import com.tripleThreads.taxiyaz.data.newRoute.Route
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class RouteRepositoryTest {


    private var context = ApplicationProvider.getApplicationContext<Context>()


    @Test
    fun getAllRoutes() {

         val route = Route(1,"Testing Title",12,2.50,2f,ArrayList(), ArrayList(),false)
         val dao = TxYzDatabase.getDatabase(context).routeDao()
         dao.insertRoute(route)
         assertNotEquals(dao.getAllRoutes(),null )


    }

    @Test
    fun setAllRoutes() {
    }

    @Test
    fun insert() {
        val route = Route(1,"Testing Title",12,2.50,2f,ArrayList(), ArrayList(), false)
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)
        assertNotEquals(dao.getRouteByName("Testing Title"),null )

    }



    @Test
    fun update() {
        val route = Route(1,"Testing Title",12,2.50,2f,ArrayList(), ArrayList(), false)
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)
        assertNotEquals(dao.getRouteByName("Testing Title"),null )


    }

    @Test
    fun delete() {
        val route = Route(1,"Testing Title",12,2.50,2f,ArrayList(), ArrayList(), false)
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)

        assertNotEquals(dao.getRouteByName("Testing Title"),null )

        dao.deleteRoute(route)

        assertEquals(dao.getRouteByName("Testing Title"),null)
    }
}