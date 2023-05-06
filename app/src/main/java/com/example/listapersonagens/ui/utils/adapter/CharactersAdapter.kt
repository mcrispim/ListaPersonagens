package com.example.listapersonagens.ui.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.listapersonagens.R
import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.databinding.ItemCharacterBinding

class CharactersAdapter :
    ListAdapter<Charackter, CharactersAdapter.CharactersViewHolder>(DIFF_CALLBACK) {
    
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Charackter>() {
            override fun areItemsTheSame(
                oldItem: Charackter,
                newItem: Charackter,
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        
            override fun areContentsTheSame(
                oldItem: Charackter,
                newItem: Charackter,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    
    class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        ViewHolder(binding.root) {
        
        fun bind(item: Charackter) {
            with(binding) {
                tvName.text = item.name
                
                Glide.with(binding.root)
                    .load(item.imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        return CharactersViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}