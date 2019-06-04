package com.tripleThreads.taxiyaz.data.user

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="users")
class User (
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name = "phone_number") @NonNull @PrimaryKey var phoneNumber:String
):Serializable