package com.example.entregapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT password FROM users WHERE userName LIKE:userName")
    fun getPasswordByUserName(userName: String): String
}