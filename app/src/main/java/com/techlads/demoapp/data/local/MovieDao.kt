package com.techlads.demoapp.data.local

import androidx.room.*
import com.techlads.demoapp.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie order by popularity DESC" )
    fun getAll(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<Movie>?)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movie: List<Movie>?)
}