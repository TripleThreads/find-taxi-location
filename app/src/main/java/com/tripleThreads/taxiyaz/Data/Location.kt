package com.tripleThreads.taxiyaz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Location")
data class Location(@NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int, @ColumnInfo(name = "lat") val lat:Double, @ColumnInfo(name="long") val lon:Double)