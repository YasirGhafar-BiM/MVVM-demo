package com.techlads.demoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techlads.demoapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao() : MovieDao
}