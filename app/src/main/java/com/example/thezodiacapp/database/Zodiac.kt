package com.example.thezodiacapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zodiac_database")
data class Zodiac(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "descr") val descr: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "month") val month: String
)

