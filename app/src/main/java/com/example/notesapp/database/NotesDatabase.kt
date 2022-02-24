package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.Dao
import com.example.notesapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notes_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance

                instance
            }
        }

    }

}