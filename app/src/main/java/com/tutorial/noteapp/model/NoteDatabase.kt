package com.tutorial.noteapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tutorial.noteapp.utilities.DateConverter
import com.tutorial.noteapp.utilities.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
        abstract fun noteDao() : NoteDatabaseDao
}
