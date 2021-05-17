package com.example.android.superhero.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.superhero.R
import com.example.android.superhero.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var _viewModel: DetailsViewModel
    private lateinit var _binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val superHero = DetailsFragmentArgs.fromBundle(requireArguments()).superhero
        _viewModel.setSuperHero(superHero)
        _binding.superHero = superHero

        setHasOptionsMenu(true)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.shareSuperHeroDetails.observe(viewLifecycleOwner) {
            it?.let { superHero ->
                shareSuperHero(superHero)
                _viewModel.onCompleteSharingSuperHer()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.m_share) {
            _viewModel.shareSuperHero(resources)
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Share SuperHero details
     */
    private fun shareSuperHero(superHeroDetails: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, superHeroDetails)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}