package com.tutorial.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.noteapp.R
import com.tutorial.noteapp.components.NoteButton
import com.tutorial.noteapp.components.NoteInputText
import com.tutorial.noteapp.data.NoteDataSource
import com.tutorial.noteapp.model.Note
import com.tutorial.noteapp.utilities.formatDate
import java.text.DateFormat
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(noteList : List<Note>,
               onAddNote: (Note) -> Unit,
               onRemoveNote: (Note) -> Unit){

    var title by remember{
        mutableStateOf("")
    }

    var description by remember{
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Notifications Icon")
        }, backgroundColor = Color(0xFFDDDDF6))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 9.dp), text = title,
                label = "Title",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                        title = it
                })

            NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 9.dp),text = description,
                label = "Add a note",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                        description = it
                })

            NoteButton(text = "Save", onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()){
                    //save the data by adding it to a list
                    onAddNote(Note(title = title,
                        description = description))
                    title = ""
                    description =""
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })

        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(noteList){ note ->
              NoteRow(note = note, onClickedNote = {
                    onRemoveNote(it)
              })
            }
        }
    }
}

@Composable
private fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onClickedNote: (Note) -> Unit){

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
            color = Color(0xFF7B94C5),
            elevation = 6.dp){

        Column(
            modifier
                .clickable {
                    onClickedNote(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp), horizontalAlignment = Alignment.Start){
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle2)
            Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.caption)

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun NoteScreenPreview(){
    NoteScreen(noteList =  NoteDataSource().loadNotes(),
        onAddNote = {},
        onRemoveNote = {})
}