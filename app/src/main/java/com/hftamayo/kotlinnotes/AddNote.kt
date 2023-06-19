package com.hftamayo.kotlinnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hftamayo.kotlinnotes.databinding.ActivityAddNoteBinding;

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_note);
    }
}