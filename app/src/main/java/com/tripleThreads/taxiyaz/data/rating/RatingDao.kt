package com.tripleThreads.taxiyaz.data.rating

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.route.Route

@Dao
interface RatingDao {
    @Query("SELECT * FROM Ratings")
    fun getAllRatings(): LiveData<List<Rating>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRating(rating: Rating)

    @Update
    fun updateRating(rating: Rating)

    @Delete
    fun deleteRating(rating: Rating)

}