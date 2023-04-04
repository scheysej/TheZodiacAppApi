package com.example.thezodiacapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Zodiac::class], version = 1)
abstract class ZodiacDatabase : RoomDatabase() {
    abstract fun zodiacDao(): ZodiacDao

    companion object{
        @Volatile
        private var INSTANCE: ZodiacDatabase? = null

        fun getDatabase(context: Context): ZodiacDatabase{
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context,
                    ZodiacDatabase::class.java, "zodiac_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}