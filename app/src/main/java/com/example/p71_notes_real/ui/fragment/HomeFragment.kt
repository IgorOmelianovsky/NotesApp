package com.example.p71_notes_real.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.p71_notes_real.R
import com.example.p71_notes_real.adapter.NoteAdapter
import com.example.p71_notes_real.databinding.FragmentHomeBinding
import com.example.p71_notes_real.ui.MainActivity
import com.example.p71_notes_real.util.Resource
import com.example.p71_notes_real.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialise()
        initialiseButtonCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initialise() = with(binding) {
        viewModel = (requireActivity() as MainActivity).viewModel
        adapter = NoteAdapter()
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        observe()
    }

    private fun observe() {
        viewModel.currentList.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> loading(true)

                is Resource.Error -> {
                    loading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    loading(false)
                    adapter.submitList(it.noteList)
                }
            }
        }

        viewModel.readNotes()
    }

    private fun loading(enabled: Boolean) = with(binding) {
        if (enabled) {
            recyclerView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun initialiseButtonCreate() = with(binding) {
        btnCreate.setOnClickListener {
            root.findNavController().navigate(R.id.action_homeFragment_to_createFragment)
        }
    }

}






















