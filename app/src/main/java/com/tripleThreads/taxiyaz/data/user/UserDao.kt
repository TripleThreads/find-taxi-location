package com.tripleThreads.taxiyaz.data.user

import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.user.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): User




}