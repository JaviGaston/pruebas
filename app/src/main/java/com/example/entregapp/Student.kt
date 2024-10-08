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
    @ColumnInfo(name = "programacion") val programacion: Int,
    @ColumnInfo(name = "basesdedatos") val basesdedatos: Int,
    @ColumnInfo(name = "sistemas") val sistemas: Int,
    @ColumnInfo(name = "marcas") val marcas: Int,
    @ColumnInfo(name = "entornos") val entornos: Int,
)