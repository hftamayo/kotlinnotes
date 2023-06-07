package com.hftamayo.kotlinnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.ViewModelProvider
import com.hftamayo.kotlinnotes.adapter.NoteAdapter
import com.hftamayo.kotlinnotes.database.NoteDatabase
import com.hftamayo.kotlinnotes.databinding.ActivityMainBinding
import com.hftamayo.kotlinnotes.models.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : NoteDatabase
    lateinit var viewModel : NoteViewModel
    lateinit var adapter : NoteAdapter
    lateinit var selectedNote : Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allnotes.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }
    }

    private fun initUI(){

    }
}