package com.kamalnayan.moviesearcher.ui.search

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kamalnayan.commons.base.BaseFragment
import com.kamalnayan.commons.extensions.loadMoreListener
import com.kamalnayan.commons.interceptor.AuthInterceptor
import com.kamalnayan.commons.state.NetworkResult
import com.kamalnayan.moviesearcher.databinding.FragmentSearchMovieBinding
import com.kamalnayan.moviesearcher.epoxy.controller.SearchMovieController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate) {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchMovieFragment.
         */

        @JvmStatic
        fun newInstance() = SearchMovieFragment()
    }

    private val controller by lazy {
        SearchMovieController()
    }

    private val viewModel by viewModels<SearchViewModel>()

    override fun fetchData() {
        viewModel.searchMovie("topgun")
    }

    override fun setViewModelToBinding() {

    }

    override fun initViews() {
        with(binding) {
            epoxyRecycler.setController(controller)
        }
    }

    override fun setData() {

    }

    override fun setListeners() {
        if (viewModel.canLoadMore) {
            binding.epoxyRecycler.loadMoreListener {
                viewModel.searchMovie("topgun")
            }
        }
    }

    override fun setObservers() {
        with(viewModel) {
            moviesList.observe(viewLifecycleOwner) { response ->
                response?.let {
                    when (it) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }

                        is NetworkResult.Loading -> {

                        }

                        is NetworkResult.Success -> {
                            controller.searchResults = it.data
                        }
                    }
                }
            }
        }
    }
}