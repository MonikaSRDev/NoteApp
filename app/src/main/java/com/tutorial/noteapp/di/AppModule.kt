package com.tutorial.noteapp.di

import android.content.Context
import androidx.room.Room
import com.tutorial.noteapp.model.NoteDatabase
import com.tutorial.noteapp.model.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NoteDatabase): NoteDatabaseDao
        = notesDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context) : NoteDatabase
        = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}