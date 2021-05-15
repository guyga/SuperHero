package com.example.android.superhero.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.databinding.FragmentDetailsBinding
import com.example.android.superhero.repository.SuperHeroRepository

class DetailsFragment : Fragment() {

    private lateinit var _viewModel: DetailsViewModel
    private lateinit var _binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(
            this,
            DetailsViewModelFactory(SuperHeroRepository.getInstance(requireActivity()))
        ).get(DetailsViewModel::class.java)

        val superHero = DetailsFragmentArgs.fromBundle(requireArguments()).superhero
        _binding.tt.text = superHero.name

        return _binding.root
    }
}