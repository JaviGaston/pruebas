package com.example.entregapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAll(): List<Student>

    @Query("SELECT * FROM students WHERE studentname LIKE :studentname AND firstSurname LIKE :firstSurname AND secondSurname LIKE :secondSurname")
    fun findByName(studentname: String, firstSurname: String, secondSurname: String): Student

    @Insert
    fun insertAll(vararg users: Student)

    @Delete
    fun delete(user: Student)
}
