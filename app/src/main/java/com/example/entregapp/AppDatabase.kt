package com.example.entregapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Student::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao(): StudentDao
}