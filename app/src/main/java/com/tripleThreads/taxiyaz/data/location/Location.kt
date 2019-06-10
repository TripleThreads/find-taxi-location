package com.tripleThreads.taxiyaz.data.location

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.lang.Exception

@Entity(tableName = "Location")
data class Location(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @Bindable @ColumnInfo(name = "name") var name: String,
    @Bindable @ColumnInfo(name = "latitude") var latitude: Double,
    @Bindable @ColumnInfo(name="longitude") var longitude: Double
): BaseObservable()