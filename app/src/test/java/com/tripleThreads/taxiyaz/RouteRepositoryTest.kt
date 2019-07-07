package com.tripleThreads.taxiyaz

import android.content.Context
import com.tripleThreads.taxiyaz.data.TxYzDatabase
import org.junit.Test
import com.tripleThreads.taxiyaz.data.route.Route
import org.junit.Assert.*
import androidx.test.core.app.ApplicationProvider





class RouteRepositoryTest {


    var context = ApplicationProvider.getApplicationContext<Context>()


    @Test
    fun getAllRoutes() {

         val route = Route(1,"Testing Title",12,12,12,12.0,12.0F, ArrayList())
         val dao = TxYzDatabase.getDatabase(context).routeDao()
         dao.insertRoute(route)
         assertNotEquals(dao.getAllRoutes(),null )


    }

    @Test
    fun setAllRoutes() {
    }

    @Test
    fun insert() {
        val route = Route(1,"Testing Title",12,12,12,12.0,12.0F, ArrayList())
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)
        assertNotEquals(dao.getRouteByName("Testing Title"),null )

    }



    @Test
    fun update() {
        val route = Route(1,"Testing Title",12,12,12,12.0,12.0F, ArrayList())
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)
        assertNotEquals(dao.getRouteByName("Testing Title"),null )


    }

    @Test
    fun delete() {
        val route = Route(1,"Testing Title",12,12,12,12.0,12.0F, ArrayList())
        val dao = TxYzDatabase.getDatabase(context).routeDao()
        dao.insertRoute(route)

        assertNotEquals(dao.getRouteByName("Testing Title"),null )

        dao.deleteRoute(route)

        assertEquals(dao.getRouteByName("Testing Title"),null)
    }
}