package com.tripleThreads.taxiyaz.data.location

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.route.Route

@Dao
interface LocationDao {
    @Query("SELECT * FROM Location")
    fun getAllLocations(): LiveData<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Update
    fun updateLocation(location: Location)

    @Delete
    fun deleteLocation(location: Location)

    @Query("SELECT * FROM LOCATION WHERE id=:locationId")
    fun getLocationById(locationId: Long) :LiveData<Location>

}