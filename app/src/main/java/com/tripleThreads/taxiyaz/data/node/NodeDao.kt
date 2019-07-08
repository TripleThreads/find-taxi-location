package com.tripleThreads.taxiyaz.data.node

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NodeDao {
    @Query("SELECT * FROM Node")
    fun getAllLocations(): LiveData<List<Node>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(node: Node)

    @Update
    fun updateLocation(node: Node)

    @Delete
    fun deleteLocation(node: Node)

    @Query("SELECT * FROM Node WHERE id=:nodeId")
    fun getLocationById(nodeId: Long) :LiveData<Node>
}