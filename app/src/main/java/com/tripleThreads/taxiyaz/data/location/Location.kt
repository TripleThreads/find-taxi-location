package com.tripleThreads.taxiyaz.data.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Location")
data class Location(
    @NotNull @PrimaryKey @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name ="name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name="longitude") val longitude: Double
)