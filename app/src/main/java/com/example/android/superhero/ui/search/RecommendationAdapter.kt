package com.example.android.superhero.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.superhero.databinding.ItemRecommendationBinding
import com.example.android.superhero.domain.model.SuperHeroRecommendation

class RecommendationAdapter :
    ListAdapter<SuperHeroRecommendation, RecommendationAdapter.RecommendationHolder>(
        RecommendationDiff()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder {
        val binding =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        val recommendation = getItem(position)
        holder.bind(recommendation)
    }

    class RecommendationHolder(private val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendation: SuperHeroRecommendation) {
            binding.recommendation = recommendation
            binding.executePendingBindings()
        }
    }

    class RecommendationDiff : DiffUtil.ItemCallback<SuperHeroRecommendation>() {
        override fun areItemsTheSame(
            oldItem: SuperHeroRecommendation,
            newItem: SuperHeroRecommendation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SuperHeroRecommendation,
            newItem: SuperHeroRecommendation
        ): Boolean {
            return oldItem == newItem
        }

    }


}