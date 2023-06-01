package com.hftamayo.kotlinnotes.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import com.hftamayo.kotlinnotes.models.Note;

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase{

    abstract fun getNoteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null
        fun getDatabase(context :Context) : NoteDatabase{
            return INSTANCE ?: synchronized (this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }

}