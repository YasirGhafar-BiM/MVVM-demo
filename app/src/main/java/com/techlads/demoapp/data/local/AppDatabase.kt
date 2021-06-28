package com.techlads.demoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.techlads.demoapp.data.GenreConverters
import com.techlads.demoapp.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao() : MovieDao
}