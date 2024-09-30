package com.mokelab.demo.feature.mokera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mokelab.demo.core.model.Mokera
import com.mokelab.demo.core.model.MokeraType
import com.mokelab.demo.feature.mokera.databinding.ItemMokeraBinding

class MokeraListAdapter : ListAdapter<Mokera, MokeraListAdapter.ViewHolder>(
    object : ItemCallback<Mokera>() {
        override fun areItemsTheSame(oldItem: Mokera, newItem: Mokera): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Mokera, newItem: Mokera): Boolean {
            return oldItem.name == newItem.name && oldItem.type == newItem.type
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMokeraBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.textName.text = item.name
        holder.binding.textType.text = item.type.toLabel()
    }

    class ViewHolder(val binding: ItemMokeraBinding) : RecyclerView.ViewHolder(binding.root)
}

private fun MokeraType.toLabel(): String {
    return when (this) {
        MokeraType.MelonSoda -> "Melon Soda"
        MokeraType.Coffee -> "Coffee"
        MokeraType.OrangeJuice -> "Orange Juice"
        MokeraType.Milk -> "Milk"
    }
}