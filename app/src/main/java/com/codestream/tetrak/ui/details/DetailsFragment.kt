package com.codestream.tetrak.ui.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.codestream.tetrak.R
import com.codestream.tetrak.data.local.Note
import com.codestream.tetrak.databinding.FragmentDetailsBinding
import com.codestream.tetrak.databinding.FragmentHomeBinding
import com.codestream.tetrak.ui.home.HomeViewModel
import com.codestream.tetrak.ui.home.NotesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    companion object {
        const val ARG_NOTE_ID = "note_id"
        fun newInstance(id: Int) = DetailsFragment().apply {
            arguments = bundleOf(
                ARG_NOTE_ID to id
            )
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteId = arguments?.getInt(ARG_NOTE_ID, -1) ?: -1
        viewModel.getNote(noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colors = requireContext().resources.getIntArray(R.array.note_colors)
        binding.fabSave.setOnClickListener {
            val selectedNote = viewModel.selectedNote.value
            if (selectedNote == null) {
                viewModel.addNote(
                    Note(
                        title = binding.edTitle.text?.toString().orEmpty(),
                        body = binding.edBody.text?.toString().orEmpty(),
                        color = colors.random(),
                    )
                )
            } else {
                viewModel.updateNote(
                    selectedNote.copy(
                        title = binding.edTitle.text?.toString().orEmpty(),
                        body = binding.edBody.text?.toString().orEmpty(),
                    )
                )
            }
        }
        binding.ivDelete.setOnClickListener {
            viewModel.deleteNote()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.selectedNote.collectLatest { note ->
                    note ?: return@collectLatest
                    binding.edTitle.setText(note.title)
                    binding.edBody.setText(note.body)

                }
            }
        }
    }

}