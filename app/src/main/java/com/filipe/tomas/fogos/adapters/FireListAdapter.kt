package com.filipe.tomas.fogos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipe.tomas.fogos.databinding.ItemFireBinding
import com.filipe.tomas.fogos.views.FireUi

class FireListAdapter(val items: List<FireUi>) : RecyclerView.Adapter<FireListAdapter.FireListViewHolder>(){
    class FireListViewHolder(val binding: ItemFireBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FireListViewHolder {
        return FireListViewHolder(
            ItemFireBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: FireListViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            holder.binding.distrito.text = items[position].district
            holder.binding.freguesia.text = items[position].district //todo change
            holder.binding.datetime.text =  items[position].getDateTime()
        }
    }

    override fun getItemCount() = items.size

}