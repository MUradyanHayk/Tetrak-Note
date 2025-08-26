package com.codestream.tetrak.di

import android.content.Context
import androidx.room.Room
import com.codestream.tetrak.data.local.NoteDatabase
import com.codestream.tetrak.data.local.NotesDao
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

    @Provides
    fun providesNoteDao(db: NoteDatabase): NotesDao {
        return db.getNoteDao()
    }
}