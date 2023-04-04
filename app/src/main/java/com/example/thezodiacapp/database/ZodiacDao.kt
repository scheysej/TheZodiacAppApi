package com.example.thezodiacapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZodiacDao {

    @Insert()
    suspend fun insert(zodiac: Zodiac)

    @Query("DELETE FROM zodiac_database")
    suspend fun deleteAll()

    @Query("SELECT * FROM zodiac_database WHERE name = :name")
    suspend fun getInfo(name: String): Zodiac?

    @Query("SELECT * FROM zodiac_database")
    suspend fun getAllNames(): List<Zodiac>?
}