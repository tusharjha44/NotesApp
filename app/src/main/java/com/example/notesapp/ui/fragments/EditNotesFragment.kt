package com.example.notesapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.DialogDeleteBinding
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class EditNotesFragment : Fragment() {

    private lateinit var binding: FragmentEditNotesBinding
    private val oldNotes by navArgs<EditNotesFragmentArgs>()
    private val viewModel: NotesViewModel by viewModels()
    private var priority: String = "1"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)


        binding.editEtTitle.setText(oldNotes.data.title)
        binding.editEtSubtitle.setText(oldNotes.data.subtitle)
        binding.editEtNotes.setText(oldNotes.data.title)

        when(oldNotes.data.priority){
            "1" -> {
                priority = "1"
                binding.editPtRed.setImageResource(0)
                binding.editPtYellow.setImageResource(0)
                binding.editPtGreen.setImageResource(R.drawable.ic_baseline_done_24)
            }
            "2" -> {
                priority = "2"
                binding.editPtRed.setImageResource(0)
                binding.editPtYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.editPtGreen.setImageResource(0)
            }
            else -> {
                priority = "3"
                binding.editPtRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.editPtYellow.setImageResource(0)
                binding.editPtGreen.setImageResource(0)
            }
        }

        binding.editPtGreen.setOnClickListener {
            priority = "1"
            binding.editPtRed.setImageResource(0)
            binding.editPtYellow.setImageResource(0)
            binding.editPtGreen.setImageResource(R.drawable.ic_baseline_done_24)
        }

        binding.editPtRed.setOnClickListener {
            priority = "3"
            binding.editPtRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.editPtYellow.setImageResource(0)
            binding.editPtGreen.setImageResource(0)
        }

        binding.editPtYellow.setOnClickListener {
            priority = "2"
            binding.editPtRed.setImageResource(0)
            binding.editPtYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.editPtGreen.setImageResource(0)
        }

        binding.saveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateNotes(view: View?) {
        val title = binding.editEtTitle.text.toString()
        val subtitle = binding.editEtSubtitle.text.toString()
        val note = binding.editEtNotes.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        val notes = Notes(
            oldNotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = note,
            date = currentDate,
            priority = priority
        )

        viewModel.updateNotes(notes)

        Toast.makeText(requireContext(),"Notes Updated Successfully!!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_notes){
            val dialog = BottomSheetDialog(requireContext(),R.style.BottomDialogStyle)
            dialog.setContentView(R.layout.dialog_delete)

            val tvYes = dialog.findViewById<TextView>(R.id.btn_yes)
            val tvNo = dialog.findViewById<TextView>(R.id.btn_no)

            tvYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                dialog.dismiss()
            }

            tvNo?.setOnClickListener {
                dialog.dismiss()

            }

            dialog.show()

        }
        return super.onOptionsItemSelected(item)
    }

}