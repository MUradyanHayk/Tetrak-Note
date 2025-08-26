package com.codestream.tetrak.data.repository

import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.data.local.NotesDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val notesDao: NotesDao) : NoteRepository {
    override fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    override fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override fun getNote(id: Int): Note? {
        return notesDao.getNote(id)
    }

    override fun getAllNotes(): List<Note> {
        return notesDao.getAllNotes()
    }


}