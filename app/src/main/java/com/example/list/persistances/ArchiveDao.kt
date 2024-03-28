package com.example.list.persistances

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.list.models.Archive

@Dao
interface ArchiveDao {
    @Query("select * from archive")
    fun loadAll(): LiveData<List<Archive>>

    @Query("select * from archive where id = :id")
    fun load(id: String): LiveData<Archive>

    @Query("select * from archive where id = :id")
    suspend fun getById(id: String): Archive?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Archive)

    @Query("delete from archive where id = :id")
    suspend fun delete(id: String)

    @Delete
    suspend fun delete(item: Archive)

    @Update
    suspend fun update(archive: Archive)
}