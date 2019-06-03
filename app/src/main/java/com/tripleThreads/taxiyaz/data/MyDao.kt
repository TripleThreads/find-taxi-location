package com.tripleThreads.taxiyaz.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface MyDao {
    @Query("SELECT * FROM ROUTES ORDER BY TITLE")
    fun getAllRoutes(): LiveData<List<Route>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route:Route)

    @Update
    fun updateRoute(route:Route)

    @Delete
    fun deleteRoute(route:Route)

    @Query("SELECT * FROM ROUTES WHERE TITLE = :title LIMIT 1")
    fun getRouteByName(title:String):LiveData<Route>




}