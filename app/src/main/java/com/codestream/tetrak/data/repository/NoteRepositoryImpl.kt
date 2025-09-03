package com.codestream.tetrak.data.repository

import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.data.local.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val notesDao: NotesDao) : NoteRepository {
    override suspend fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override suspend fun getNote(id: Int): Note? {
        return notesDao.getNote(id)
    }

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }


}