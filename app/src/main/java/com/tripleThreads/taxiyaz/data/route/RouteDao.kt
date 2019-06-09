package com.tripleThreads.taxiyaz.data.route

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.route.Route

@Dao
interface RouteDao {
    @Query("SELECT * FROM ROUTES ORDER BY number_of_hops")
    fun getAllRoutes(): LiveData<List<Route>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: Route)

    @Update
    fun updateRoute(route: Route)

    @Delete
    fun deleteRoute(route: Route)

    @Query("SELECT * FROM ROUTES WHERE TITLE = :title ORDER BY price")
    fun getRouteByName(title:String):LiveData<List<Route>>

    @Query("DELETE FROM ROUTES")
    fun deleteAll()


}