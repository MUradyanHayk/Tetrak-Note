package com.codestream.tetrak.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    val selectedNote = MutableStateFlow<Note?>(null)
    fun getNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getNote(id)?.let {
                selectedNote.value = it
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(/*note: Note*/) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(selectedNote.value ?: return@launch)
        }
    }
}