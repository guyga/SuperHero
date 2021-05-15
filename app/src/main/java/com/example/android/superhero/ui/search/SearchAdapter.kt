package com.example.android.superhero.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.superhero.databinding.ItemSearchResultBinding
import com.example.android.superhero.domain.model.SuperHero

class SearchAdapter(
    private val onSuperHeroSelectedListener: OnSuperHeroSelectedListener
) : ListAdapter<SuperHero, SearchAdapter.SuperHeroHolder>(SuperHeroDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperHeroHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroHolder, position: Int) {
        val superHero = getItem(position)
        holder.bind(superHero, onSuperHeroSelectedListener)
    }

    class SuperHeroHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(superHero: SuperHero, onSuperHeroSelectedListener: OnSuperHeroSelectedListener) {
            binding.root.setOnClickListener {
                onSuperHeroSelectedListener.onSuperHeroSelected(
                    superHero
                )
            }
            binding.superHero = superHero
            binding.executePendingBindings()
        }
    }

    class SuperHeroDiff : DiffUtil.ItemCallback<SuperHero>() {
        override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem == newItem
        }
    }
}