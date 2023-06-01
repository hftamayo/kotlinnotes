package com.hftamayo.kotlinnotes.database

import androidx.lifecycle.LiveData
import com.hftamayo.kotlinnotes.models.Note

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()
}