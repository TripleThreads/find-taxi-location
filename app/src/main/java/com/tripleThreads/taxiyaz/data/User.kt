package com.tripleThreads.taxiyaz.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="users")
class User (
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name = "Phone Number") @NonNull @PrimaryKey var phoneNumber:String
):Serializable