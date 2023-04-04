package com.tutorial.noteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorial.noteapp.data.NoteDataSource
import com.tutorial.noteapp.model.Note
import com.tutorial.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
//    private var notesList = mutableStateListOf<Note>()

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
//        notesList.addAll(NoteDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                    if(listOfNotes.isEmpty()){
                        Log.d("Empty List", ": Notes list is empty")
                    }else{
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

//    fun getAllNotesList() : List<Note>{
//        return notesList
//    }
//
//    fun addNote(note : Note){
//        notesList.add(note)
//    }
//
//    fun removeNote(note: Note){
//        notesList.remove(note)
//    }

     fun addNote(note: Note) = viewModelScope.launch { repository.insertNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
     fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}