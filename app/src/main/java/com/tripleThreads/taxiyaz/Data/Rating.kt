package com.tripleThreads.taxiyaz.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tripleThreads.taxiyaz.Data.Route

@Entity(tableName = "Ratings")
data class Rating(@NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id:Int, @ColumnInfo(name="roadId") val roadId: Route, @ColumnInfo(name="userId") val userId:User, @ColumnInfo(name="rate") val rate:Double)