package com.codestream.tetrak.data.repository

import com.codestream.tetrak.data.local.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNote(id: Int): Note?
    suspend fun getAllNotes(): Flow<List<Note>>
}