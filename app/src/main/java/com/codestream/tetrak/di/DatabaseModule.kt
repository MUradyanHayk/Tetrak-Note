package com.codestream.tetrak.di

import android.content.Context
import androidx.room.Room
import com.codestream.tetrak.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {
    @Provides
    fun providesNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "Notes.db").build()
    }
}