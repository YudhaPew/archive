package com.example.list.persistances

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.list.models.Archive

@Database(entities = [Archive::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun archiveDao(): ArchiveDao

    companion object {
        const val DATABASE_NAME = "archive-database"
    }
}