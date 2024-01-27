package com.kamalnayan.domain.usecase

import android.util.Log
import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.base.BaseUseCase
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.kamalnayan.domain.repository.IMovieRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/** @Author Kamal Nayan
Created on: 27/01/24
 **/
class GetMovieDetailsUseCase @Inject constructor(
    private val repository: IMovieRepository
) : BaseUseCase<Pair<String, @PlotType String>, ApiResponse<MovieDetailsResponse>?>() {
    override suspend fun invoke(params: Pair<String, @PlotType String>?): ApiResponse<MovieDetailsResponse>? {
        val id = params?.first
        val plot = params?.second
        Log.d("details", "getMovieDetails use case: on success plot and id")

        if (isValidId(id) && isValidPlot(plot))
            return repository.getMovieDetails(id, plot)

        return null
    }

    @OptIn(ExperimentalContracts::class)
    private fun isValidPlot(plot: String?): Boolean {
        contract {
            returns() implies(plot !=null)
        }

        return plot.isNullOrBlank().not()
    }

    @OptIn(ExperimentalContracts::class)
    private fun isValidId(id: String?): Boolean {
        contract {
            returns() implies (id != null)
        }
        return id.isNullOrBlank().not()
    }
}