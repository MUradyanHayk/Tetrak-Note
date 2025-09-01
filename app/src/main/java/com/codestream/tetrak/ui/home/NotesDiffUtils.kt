package com.codestream.tetrak.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.codestream.tetrak.data.local.Note

class NotesDiffUtils: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}