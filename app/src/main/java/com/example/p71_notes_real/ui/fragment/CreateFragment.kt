package com.example.p71_notes_real.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.p71_notes_real.R
import com.example.p71_notes_real.databinding.FragmentCreateBinding
import com.example.p71_notes_real.model.NoteModel
import com.example.p71_notes_real.ui.MainActivity
import com.example.p71_notes_real.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater)
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

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (title.isNotBlank() && content.isNotBlank()) {
                val model = NoteModel(title, content)
                viewModel.insertNote(model).invokeOnCompletion {
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

















