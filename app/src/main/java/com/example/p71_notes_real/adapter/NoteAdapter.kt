package com.example.p71_notes_real.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p71_notes_real.R
import com.example.p71_notes_real.databinding.AdapterItemBinding
import com.example.p71_notes_real.model.NoteModel
import com.example.p71_notes_real.ui.fragment.HomeFragmentDirections

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterItemBinding.bind(itemView)

        fun bindData(noteModel: NoteModel) = with(binding) {
            tvTitle.text = noteModel.title
            tvContent.text = noteModel.content

            itemView.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(noteModel)
                root.findNavController().navigate(direction)
            }
        }

    }

    class Comparator : DiffUtil.ItemCallback<NoteModel>() {

        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

}