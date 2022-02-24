package com.example.notesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.ui.fragments.HomeFragmentDirections
import java.util.ArrayList

class NotesAdapter(val context: Context, private var notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNotesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = notesList[position]

        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text = data.subtitle
        holder.binding.date.text = data.date

        when(data.priority){
            "1" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)
            }
            else -> {
                holder.binding.priority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount() = notesList.size

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

}