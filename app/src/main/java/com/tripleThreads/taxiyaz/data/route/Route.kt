package com.tripleThreads.taxiyaz.data.route

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.tripleThreads.taxiyaz.data.location.Location
import java.io.Serializable

@Entity(tableName = "Routes")
@TypeConverters(LocationConverter::class)
class Route (
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name="route_id") val routeId:Long,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "start_node") val startId: Long,
    @ColumnInfo(name ="destination_node") val destinationId: Long,
    @ColumnInfo(name = "number_of_hops")val hops: Int,
    @ColumnInfo(name = "price")val price: Double,
    @ColumnInfo(name = "locations") var locations: ArrayList<Location>
):Serializable