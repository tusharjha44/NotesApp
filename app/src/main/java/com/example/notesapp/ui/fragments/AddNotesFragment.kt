package com.example.notesapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNotesFragment : Fragment() {

    private var priority: String = "1"
    private lateinit var binding: FragmentAddNotesBinding

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNotesBinding.inflate(layoutInflater,container,false)

        binding.addPtGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.addPtGreen.setOnClickListener {
            priority = "1"
            binding.addPtRed.setImageResource(0)
            binding.addPtYellow.setImageResource(0)
            binding.addPtGreen.setImageResource(R.drawable.ic_baseline_done_24)
        }

        binding.addPtRed.setOnClickListener {
            priority = "3"
            binding.addPtRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.addPtYellow.setImageResource(0)
            binding.addPtGreen.setImageResource(0)
        }

        binding.addPtYellow.setOnClickListener {
            priority = "2"
            binding.addPtRed.setImageResource(0)
            binding.addPtYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.addPtGreen.setImageResource(0)
        }


        binding.saveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun createNotes(view: View?) {

        val title = binding.addEtTitle.text.toString()
        val subtitle = binding.addEtSubtitle.text.toString()
        val note = binding.addEtNotes.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        val notes = Notes(
            null,
            title = title,
            subtitle = subtitle,
            notes = note,
            date = currentDate,
            priority = priority
        )

        notesViewModel.addNotes(notes)

        Toast.makeText(requireContext(),"Notes Created Successfully!!",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(requireView()).navigate(R.id.action_addNotesFragment_to_homeFragment)

    }

}