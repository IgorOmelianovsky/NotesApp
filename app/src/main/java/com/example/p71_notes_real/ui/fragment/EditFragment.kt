package com.example.p71_notes_real.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.p71_notes_real.R
import com.example.p71_notes_real.databinding.FragmentEditBinding
import com.example.p71_notes_real.databinding.FragmentHomeBinding
import com.example.p71_notes_real.model.NoteModel
import com.example.p71_notes_real.ui.MainActivity
import com.example.p71_notes_real.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private val arguments: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialise()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initialise() = with(binding) {
        viewModel = (requireActivity() as MainActivity).viewModel
        val currentModel = arguments.noteModel

        etTitle.setText(currentModel.title)
        etContent.setText(currentModel.content)

        btnDelete.setOnClickListener {
            viewModel.deleteNote(currentModel).invokeOnCompletion {
                lifecycleScope.launch(Dispatchers.Main) {
                    root.findNavController().popBackStack()
                }
            }
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()
            val id = currentModel.id

            if (title.isNotBlank() && content.isNotBlank()) {
                val model = NoteModel(title, content, id)
                viewModel.updateNote(model).invokeOnCompletion {
                    lifecycleScope.launch(Dispatchers.Main) {
                        root.findNavController().popBackStack()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "All fields are mandatory", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}





















