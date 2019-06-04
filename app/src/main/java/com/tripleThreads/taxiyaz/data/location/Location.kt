package com.tripleThreads.taxiyaz.data.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Location")
data class Location(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name="longitude") val longitude: Double
)