package com.example.entregapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "studentName") val studentname: String,
    @ColumnInfo(name = "firstSurname") val firstSurname: String,
    @ColumnInfo(name = "secondSurname") val secondSurname: String,
    @ColumnInfo(name = "Calification") val calification: Int
)