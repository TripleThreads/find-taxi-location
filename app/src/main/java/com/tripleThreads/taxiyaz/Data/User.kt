package com.tripleThreads.taxiyaz.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="users")
class User (@NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id:Int, @ColumnInfo(name="name") val name:String, @ColumnInfo(name = "Phone Number") var phoneNumber:String?= null)