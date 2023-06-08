package com.hftamayo.kotlinnotes.models

import android.app.Application
import com.hftamayo.kotlinnotes.database.NoteDatabase
import com.hftamayo.kotlinnotes.database.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : NoteRepository
    val allnotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
}