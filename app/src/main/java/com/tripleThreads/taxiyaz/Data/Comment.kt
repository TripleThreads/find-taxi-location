package com.tripleThreads.taxiyaz.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Comments")
data class Comment(@NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int, @ColumnInfo(name="userId") val user:User, @ColumnInfo(name="comment") val comment: Comment )