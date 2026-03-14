package com.example.quotesapp2026.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert
    suspend fun insert(user: UserTable)

    @Query("SELECT * FROM UserTable")
    fun getUser() : LiveData<List<UserTable>>


    @Query("SELECT * FROM UserTable LIMIT 1")
    fun getOneQuote() : LiveData<UserTable>


}