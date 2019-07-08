package com.tripleThreads.taxiyaz.data.node

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Node")
data class Node (
    @PrimaryKey @NotNull @ColumnInfo(name="id") val id: Long,
    @Bindable @ColumnInfo(name="name") var name: String,
    @Bindable @ColumnInfo(name="latitude") var latitude: Double,
    @Bindable @ColumnInfo(name="longitude") var longitude: Double
    //val availableNode: AvailableNode
): BaseObservable()