package com.kamalnayan.moviesearcher.ui.search

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamalnayan.commons.annotation.ViewType
import com.kamalnayan.commons.base.BaseFragment
import com.kamalnayan.commons.extensions.loadMoreListener
import com.kamalnayan.commons.modifier.SearchResultModifier
import com.kamalnayan.moviesearcher.R
import com.kamalnayan.moviesearcher.databinding.FragmentSearchMovieBinding
import com.kamalnayan.moviesearcher.epoxy.controller.SearchMovieController
import com.kamalnayan.moviesearcher.ui.bts.BtsSearchResultModifier
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate) {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchMovieFragment.
         */

        @JvmStatic
        fun newInstance() = SearchMovieFragment()
    }

    private val controller by lazy {
        SearchMovieController()
    }


    private var selectedViewType: @ViewType Int = ViewType.VIEW_TYPE_GRID

    private val viewModel by viewModels<SearchViewModel>()

    /**
     * used to hold currently selected sort option.
     * By default [SearchResultModifier.SortByDefault] is selected
     */
    private var selectedModifier: SearchResultModifier = SearchResultModifier.SortByDefault
        set(value) {
            field = value
            viewModel.applyModifier(value)
            setIndicatorVisibility()
        }

    override fun fetchData() {
        viewModel.searchMovie("top")
    }

    override fun setViewModelToBinding() {
        binding.vm = viewModel
    }

    override fun initViews() {
        with(binding) {
            toolbar.tvTitle.text = R.string.text_movie_searcher.toStringFromResourceId()
            epoxyRecycler.setController(controller)
        }
    }

    override fun setData() {
        // do nothing
    }

    override fun setListeners() {
        with(binding)
        {
            epoxyRecycler.loadMoreListener(threshold = 1) {
                if (viewModel.canLoadMore) {
                    Log.d("load-more", "setListeners: FETCHING more data  ")
                    viewModel.searchMovie("top")
                }
            }

            toolbar.ivViewTypeSelector.setOnClickListener {
                handleViewTypeSelectorClick()
            }

            toolbar.ivMore.setOnClickListener {
                handleShowFilterClick()
            }
        }
    }

    override fun setObservers() {
        with(viewModel) {
            moviesList.observe(viewLifecycleOwner) { response ->
                response?.let {
                    controller.apply {
                        searchResults = it
                        canLoadMore = viewModel.canLoadMore
                    }
                }
            }
        }
    }

    /**
     * Sets visibility of indicator to show if any sorting is applied or not
     */
    private fun setIndicatorVisibility() {
        binding.toolbar.ivFilterActive.isVisible =
            selectedModifier !is SearchResultModifier.SortByDefault
    }

    /**
     * Shows [BtsSearchResultModifier] when [R.id.iv_more]
     * is clicked
     */
    private fun handleShowFilterClick() {
        val bottomSheet = BtsSearchResultModifier()
        bottomSheet.apply {
            setSelectedModifier(this@SearchMovieFragment.selectedModifier)
            setSelectionChangedListener {
                this@SearchMovieFragment.selectedModifier = it
            }
        }
        bottomSheet.show(childFragmentManager, null)
    }

    /**
     * Updates layout manager on click of [R.id.iv_view_type_selector]
     * [GridLayoutManager] or [LinearLayoutManager] is applied to
     * [R.id.epoxy_recycler] as per user selection
     */
    private fun handleViewTypeSelectorClick() {
        with(binding) {
            when (selectedViewType) {
                ViewType.VIEW_TYPE_LIST -> {
                    selectedViewType = ViewType.VIEW_TYPE_GRID
                    epoxyRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
                    setSelectorImageRes(R.drawable.ic_grid)
                }

                ViewType.VIEW_TYPE_GRID -> {
                    selectedViewType = ViewType.VIEW_TYPE_LIST
                    epoxyRecycler.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    setSelectorImageRes(R.drawable.ic_list)
                }
            }
        }
    }

    private fun setSelectorImageRes(@DrawableRes resId: Int) {
        binding.toolbar.ivViewTypeSelector.setImageResource(resId)
    }
}