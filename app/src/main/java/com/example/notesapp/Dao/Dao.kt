package com.example.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.notesapp.model.Notes


@Dao
interface Dao {

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes_table WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)

}