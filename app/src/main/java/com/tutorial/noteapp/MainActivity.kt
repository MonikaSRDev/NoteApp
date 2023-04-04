package com.tutorial.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutorial.noteapp.model.Note
import com.tutorial.noteapp.screen.NoteScreen
import com.tutorial.noteapp.screen.NoteViewModel
import com.tutorial.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                //both are same
                val noteViewModel = viewModel<NoteViewModel>()
//                val noteViewModel: NoteViewModel by viewModels()
                NoteApp(noteViewModel)
            }
        }
    }
}

@Composable
private fun NoteApp(noteViewModel: NoteViewModel){
    //to get the noteList value as it is of type State
    val notesList = noteViewModel.noteList.collectAsState().value
    NoteScreen(noteList =  notesList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) })
}
