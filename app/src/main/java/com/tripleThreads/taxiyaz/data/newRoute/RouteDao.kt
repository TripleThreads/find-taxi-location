package com.tripleThreads.taxiyaz.data.newRoute

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RouteDao {
    @Query("SELECT * FROM ROUTE ORDER BY number_of_hops")
    fun getAllRoutes(): LiveData<List<Route>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: Route)

    @Update
    fun updateRoute(route: Route)

    @Delete
    fun deleteRoute(route: Route)

    @Query("SELECT * FROM ROUTE WHERE TITLE = :title ORDER BY price")
    fun getRouteByName(title:String):LiveData<List<Route>>

    @Query("DELETE FROM ROUTE")
    fun deleteAll()

}