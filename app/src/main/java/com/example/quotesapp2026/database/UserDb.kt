package com.example.quotesapp2026.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserTable::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase(){


    abstract fun userdao() : UserDAO



}