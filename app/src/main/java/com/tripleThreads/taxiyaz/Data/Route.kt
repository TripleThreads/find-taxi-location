package com.tripleThreads.taxiyaz.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Routes")
class Route (
    @PrimaryKey @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "lat")val latitude: Double,
    @ColumnInfo(name = "lon")val logitude: Double,
    @ColumnInfo(name = "hop")val hops: Int,
    @ColumnInfo(name = "price")val price: Double
)