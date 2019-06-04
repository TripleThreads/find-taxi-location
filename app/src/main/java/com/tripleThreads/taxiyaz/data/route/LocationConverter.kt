package com.tripleThreads.taxiyaz.data.route

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tripleThreads.taxiyaz.data.location.Location


class LocationConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<Location> {
        val listType = object : TypeToken<ArrayList<Location>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Location>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}