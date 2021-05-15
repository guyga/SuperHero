package com.example.android.superhero.ui.search

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.superhero.R
import com.example.android.superhero.databinding.FragmentSearchBinding
import com.example.android.superhero.domain.model.SuperHero
import com.example.android.superhero.repository.SuperHeroRepository

class SearchFragment : Fragment() {

    private lateinit var _viewModel: SearchViewModel
    private lateinit var _binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(SuperHeroRepository.getInstance(requireActivity()))
        ).get(SearchViewModel::class.java)

        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val superHeroSelectedListener = OnSuperHeroSelectedListener(::onSuperHeroSelected)

        _binding.searchResults.adapter = SearchAdapter(superHeroSelectedListener)
        _binding.recommendations.adapter = RecommendationAdapter(superHeroSelectedListener)
        _binding.searchResults.addItemDecoration(
            HorizontalSpacingItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.spacing_default
                )
            )
        )
        _binding.recommendations.addItemDecoration(
            HorizontalSpacingItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.spacing_default
                )
            )
        )

        _viewModel.loadingRecommendations.observe(viewLifecycleOwner) {
            if (!it) {
                setHasOptionsMenu(true)
            }
        }

        _viewModel.navigateToDetails.observe(viewLifecycleOwner) {
            it?.let { superHero ->
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                        superHero
                    )
                )
                _viewModel.onNavigateToDetailsComplete()
            }
        }
    }

    private fun onSuperHeroSelected(superHero: SuperHero) {
        _viewModel.navigateToDetails(superHero)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val searchView: SearchView = menu.findItem(R.id.m_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                if (query != null)
                    _viewModel.searchSuperHero(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun hideKeyboard() {
        this.view?.let {
            val view = it.findFocus() ?: it
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    class HorizontalSpacingItemDecoration(private val spacingPx: Int) :
        RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val pos = parent.getChildAdapterPosition(view)
            val applyMargin = pos != parent.adapter!!.itemCount - 1
            if (applyMargin)
                outRect.right = spacingPx
        }
    }
}