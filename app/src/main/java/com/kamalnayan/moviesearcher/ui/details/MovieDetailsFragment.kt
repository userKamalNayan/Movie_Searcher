package com.kamalnayan.moviesearcher.ui.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.base.BaseFragment
import com.kamalnayan.moviesearcher.databinding.FragmentMovieDetailsBinding
import com.kamalnayan.moviesearcher.epoxy.controller.DetailsController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val controller by lazy { DetailsController() }

    override fun fetchData() {
        viewModel.getMovieDetails(args.imdbId.orEmpty(), PlotType.PLOT_TYPE_FULL)
    }

    override fun setViewModelToBinding() {
        binding.vm = viewModel
    }

    override fun initViews() {
        with(binding) {
            epoxyRecycler.setController(controller)
        }
    }

    override fun setData() {

    }

    override fun setListeners() {
    }

    override fun setObservers() {
        with(viewModel) {
            movieDetails.observe(viewLifecycleOwner) { response ->
                response?.let {
                    controller.movieDetail = it
                }
            }
        }
    }

}