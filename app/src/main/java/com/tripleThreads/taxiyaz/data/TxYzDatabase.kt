package com.tripleThreads.taxiyaz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.comment.CommentDao
import com.tripleThreads.taxiyaz.data.location.Location
import com.tripleThreads.taxiyaz.data.location.LocationDao
import com.tripleThreads.taxiyaz.data.rating.Rating
import com.tripleThreads.taxiyaz.data.rating.RatingDao
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.data.route.RouteDao
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.data.user.UserDao

@Database(
    entities = [
        User::class,
        Route::class,
        Comment::class,
        Location::class,
        Rating::class],
    version = 3)
abstract class TxYzDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun routeDao(): RouteDao
    abstract fun commentDao(): CommentDao
    abstract fun locationDao(): LocationDao
    abstract fun ratingDao(): RatingDao



    companion object {
        @Volatile
        private var INSTANCE: TxYzDatabase? = null

        fun getDatabase(context: Context): TxYzDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TxYzDatabase::class.java, "TxYzDatabase"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
