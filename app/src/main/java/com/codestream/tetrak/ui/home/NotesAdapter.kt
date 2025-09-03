package com.codestream.tetrak.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.databinding.NoteItemBinding
import java.lang.ref.WeakReference

interface NoteItemDelegate {
    fun onClick(id: Int)
}

class NotesAdapter(var delegate: WeakReference<NoteItemDelegate>? = null) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(NotesDiffUtils()) {

    class NoteViewHolder(val binding: NoteItemBinding, val delegate: WeakReference<NoteItemDelegate>?) :
        ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.titleTextView.text = note.title
            binding.bodyTextView.text = note.body
            binding.root.setOnClickListener {
                delegate?.get()?.onClick(note.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    }
}