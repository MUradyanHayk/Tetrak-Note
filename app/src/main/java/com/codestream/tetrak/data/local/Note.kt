package com.codestream.tetrak.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val body: String,
    val color: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)