package com.tutorial.noteapp.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    //Flow is a part of coroutine - sequential execution inside a coroutine
    @Query("SELECT * FROM notes_tbl")
    fun getNotesList() : Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl WHERE id =:id")
    fun getNote(id: String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Query("DELETE FROM notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)


}
