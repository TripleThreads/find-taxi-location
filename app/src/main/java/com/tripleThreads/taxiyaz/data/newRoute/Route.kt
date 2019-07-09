package com.tripleThreads.taxiyaz.data.newRoute

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "Route")
@TypeConverters(DoubleConverter::class)
data class Route (
    @NotNull @PrimaryKey @ColumnInfo(name="route_id") val routeId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "number_of_hops") val hops: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "latitudes") var latitudes: ArrayList<Double>,
    @ColumnInfo(name = "longitudes") var longitudes: ArrayList<Double>
):Serializable


class DoubleConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<Double> {
        val listType = object : TypeToken<ArrayList<Double>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Double>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
