package com.example.a20202212arielapplebaumnycschoolskotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import com.example.a20202212arielapplebaumnycschoolskotlin.databinding.SchoolItemBinding

class SchoolsAdapter(private val listener : OnItemClickListener) : ListAdapter<School,SchoolsAdapter.SchoolsViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolsViewHolder {
        val binding = SchoolItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SchoolsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class SchoolsViewHolder(private val binding : SchoolItemBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener{
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val school = getItem(position)
                        listener.onItemClicked(school)
                    }
                }
            }
        }

        fun bind(school : School){
            binding.apply {
                itemName.text = school.name
                itemDescription.text = school.phone
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(school: School)
    }

    class DiffCallBack : DiffUtil.ItemCallback<School>(){

        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem == newItem
    }
}