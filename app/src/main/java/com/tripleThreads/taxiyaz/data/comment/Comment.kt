package com.tripleThreads.taxiyaz.data.comment

import androidx.room.*
import com.tripleThreads.taxiyaz.data.route.Route
import org.jetbrains.annotations.NotNull
import java.util.*


@Entity(tableName = "Comments", foreignKeys = [



    ForeignKey(
        entity = Route::class,
        parentColumns = ["route_id"],
        childColumns = ["route_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(DateConverter::class)
data class Comment(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name="route_id") val routeId: Int,
    @ColumnInfo(name="userId") val userId: String,
    @ColumnInfo(name="comment") val comment: String,
    @ColumnInfo(name = "date") val date: Date)