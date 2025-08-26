package com.codestream.tetrak.di

import com.codestream.tetrak.data.repository.NoteRepository
import com.codestream.tetrak.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNoteRepository(noteRepository: NoteRepositoryImpl): NoteRepository
}