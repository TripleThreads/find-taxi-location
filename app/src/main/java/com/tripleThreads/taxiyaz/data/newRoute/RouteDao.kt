package com.tripleThreads.taxiyaz.data.newRoute

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RouteDao {
    @Query("SELECT * FROM ROUTE")
    fun getAllRoutes(): LiveData<List<Route>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: Route)

    @Update
    fun updateRoute(route: Route)

    @Delete
    fun deleteRoute(route: Route)

    @Query("SELECT * FROM ROUTE WHERE TITLE Like :title ORDER BY price")
    fun getRouteByName(title:String):LiveData<List<Route>>

    @Query("DELETE FROM ROUTE")
    fun deleteAll()

    @Query("SELECT * FROM ROUTE WHERE route_id =:id")
    fun getRouteById(id: Long): Route

    @Query("SELECT * FROM ROUTE WHERE bookmark=1")
    fun getBookMarked(): LiveData<List<Route>>

}