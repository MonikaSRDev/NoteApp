package com.tutorial.noteapp.repository

import com.tutorial.noteapp.model.Note
import com.tutorial.noteapp.model.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    fun insertNote(note: Note) = noteDatabaseDao.insertNote(note)
    fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao.getNotesList().flowOn(Dispatchers.IO).conflate()

}