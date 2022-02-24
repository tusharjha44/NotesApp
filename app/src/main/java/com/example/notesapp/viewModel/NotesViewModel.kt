package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.model.Notes
import com.example.notesapp.repository.NotesRepository

class NotesViewModel(application:Application) : AndroidViewModel(application){

    private var repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

}

//class NotesViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return NotesViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}