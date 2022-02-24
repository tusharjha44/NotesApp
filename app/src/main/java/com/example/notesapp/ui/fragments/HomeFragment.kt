package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.adapters.NotesAdapter
import com.example.notesapp.viewModel.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val notesViewModel: NotesViewModel by viewModels()
    var myOldNotes: ArrayList<Notes> = arrayListOf()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.addNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addNotesFragment)
        }

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.rvHome.layoutManager = staggeredGridLayoutManager

        notesViewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            myOldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rvHome.adapter = adapter

        }

        binding.filter.setOnClickListener {
            notesViewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvHome.adapter = adapter

            }
        }

        binding.high.setOnClickListener {
            notesViewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvHome.adapter = adapter

            }
        }

        binding.medium.setOnClickListener {
            notesViewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvHome.adapter = adapter

            }
        }

        binding.low.setOnClickListener {
            notesViewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                myOldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rvHome.adapter = adapter

            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.serach_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter notes name here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(noteTag: String?): Boolean {
                notesFilter(noteTag)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFilter(noteTag: String?) {
        val newFilteredList = arrayListOf<Notes>()

        for (i in myOldNotes) {
            if(i.title.contains(noteTag!!) || i.subtitle.contains(noteTag)){
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)

    }

}
