package com.tripleThreads.taxiyaz.data.comment

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.*
import com.tripleThreads.taxiyaz.data.newRoute.Route
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
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Long,
    @ColumnInfo(name="route_id") var routeId: Long,
    @ColumnInfo(name="userId") val userId: String,
    @Bindable @ColumnInfo(name="comment") var comment: String,
    @ColumnInfo(name = "date") val date: Date): BaseObservable()