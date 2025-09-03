package com.codestream.tetrak.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codestream.tetrak.MainActivity
import com.codestream.tetrak.R
import com.codestream.tetrak.databinding.FragmentHomeBinding
import com.codestream.tetrak.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class HomeFragment : Fragment(), NoteItemDelegate {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: NotesAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            onClick(-1)
        }
        adapter = NotesAdapter(WeakReference(this))
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.setReverseLayout(true)
        binding.rvNotes.layoutManager = layoutManager
        binding.rvNotes.adapter = adapter


        lifecycleScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.allNotes.collectLatest { notes ->
                    adapter?.submitList(notes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Int) {
        val mainActivity = activity as MainActivity
        val bundle = Bundle()
        bundle.putInt(DetailsFragment.ARG_NOTE_ID, id)
        lifecycleScope.launch(Dispatchers.Main) {
            findNavController().navigate(R.id.detailsFragment, bundle)
        }
    }
}
