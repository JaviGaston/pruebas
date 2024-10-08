package com.example.entregapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "password") val password: String
)
