package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.Dao
import com.example.notesapp.model.Notes

class NotesRepository(private val notesDao: Dao) {

    fun getAllNotes(): LiveData<List<Notes>> = notesDao.getNotes()

    fun getHighNotes(): LiveData<List<Notes>> = notesDao.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = notesDao.getMediumNotes()

    fun getLowNotes(): LiveData<List<Notes>> = notesDao.getLowNotes()

    fun insertNotes(notes: Notes){
        return notesDao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        return notesDao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        return notesDao.updateNotes(notes)
    }

}