package com.tripleThreads.taxiyaz.data.rating

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.data.user.User

@Entity(tableName = "Ratings", foreignKeys = [
    ForeignKey(entity = User::class,
        parentColumns = arrayOf("phone_number"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE),

    ForeignKey(
        entity = Route::class,
        parentColumns = ["route_id"],
        childColumns = ["route_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Rating(
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id:Int,
    @ColumnInfo(name="route_id") val routeId: Int,
    @ColumnInfo(name="userId") val userId: String,
    @ColumnInfo(name="rate") val rate:Double)