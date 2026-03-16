package com.example.quotesapp2026.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuotesTable(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "author")
    val a: String,

    @ColumnInfo(name = "content")
    val c: String,

    @ColumnInfo(name = "tags")
    val h: String,

    @ColumnInfo(name = "quote")
    val q: String
)
