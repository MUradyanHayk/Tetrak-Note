package com.codestream.tetrak.data.repository

import com.codestream.tetrak.data.local.Note

interface NoteRepository {
    fun addNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(note: Note)
    fun getNote(id: Int): Note?
    fun getAllNotes(): List<Note>
}