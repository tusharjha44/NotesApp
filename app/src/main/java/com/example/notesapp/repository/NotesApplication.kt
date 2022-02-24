package com.example.notesapp.repository

import android.app.Application
import com.example.notesapp.database.NotesDatabase

class NotesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { NotesDatabase.getDatabaseInstance(this) }
    val repository by lazy { NotesRepository(database.notesDao()) }
}