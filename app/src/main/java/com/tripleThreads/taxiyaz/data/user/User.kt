package com.tripleThreads.taxiyaz.data.user

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="users")
class User (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @Bindable @ColumnInfo(name="name")var name: String,
    @Bindable @ColumnInfo(name = "phone_number")@NonNull var phoneNumber: String
):Serializable, BaseObservable()