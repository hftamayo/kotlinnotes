package com.hftamayo.kotlinnotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.LinearLayout
import android.widget.SearchView
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
        database = NoteDatabase.getDatabase(this)
    }

    private fun initUI(){
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter = NotesAdapter(this, this)
        binding.recyclerView.adapter = adapter

        val getcontent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val note = result.data?.getSerializableExtra("note") as? Note
                if(note != null){
                    viewModel.insertNote(note)
                }
            }
        }
        binding.fbAddNote.setOnclickListener {
            val intent = Intent(this, AddNote::class.java)
            getContent.launch(intent)
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean{
                if(newText != null){
                    adapter.filterList(newText)
                }
                return true
            }
        })

    }
}