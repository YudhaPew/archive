package com.example.list.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Archive(
    @PrimaryKey
    val id: String,
    val nama: String,
    val alamat: String,
    val tanggal: String,
    val materi : String
)
