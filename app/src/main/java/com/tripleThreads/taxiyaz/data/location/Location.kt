package com.tripleThreads.taxiyaz.data.location

import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Location")
data class Location(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @Bindable @ColumnInfo(name = "name") val name: String,
    @Bindable @ColumnInfo(name = "latitude") val latitude: Double,
    @Bindable @ColumnInfo(name="longitude") val longitude: Double
)