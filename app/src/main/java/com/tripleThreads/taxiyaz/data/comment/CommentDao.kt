package com.tripleThreads.taxiyaz.data.comment

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.tripleThreads.taxiyaz.data.route.Route

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comments ORDER BY date")
    fun getAllComments(): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: Comment)

    @Update
    fun updateComment(comment: Comment)

    @Delete
    fun deleteComment(comment: Comment)

}