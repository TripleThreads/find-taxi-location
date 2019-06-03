package com.tripleThreads.taxiyaz.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser():User




}