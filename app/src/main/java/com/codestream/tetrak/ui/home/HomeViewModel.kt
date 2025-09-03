package com.codestream.tetrak.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.data.repository.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val notesRepository: NoteRepositoryImpl) : ViewModel() {
    var allNotes = MutableStateFlow(emptyList<Note>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getAllNotes().collectLatest {notes->
                allNotes.value = notes
            }
        }
    }
}