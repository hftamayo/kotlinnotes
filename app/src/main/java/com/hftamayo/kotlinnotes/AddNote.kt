package com.hftamayo.kotlinnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.hftamayo.kotlinnotes.databinding.ActivityAddNoteBinding;
import com.hftamayo.kotlinnotes.models.Note

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note : Note
    private lateinit var old_note : Note
    var isUpdate = false

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root);
        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        }
    }
}