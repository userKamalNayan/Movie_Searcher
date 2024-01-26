package com.kamalnayan.domain.usecase

import com.kamalnayan.commons.base.BaseUseCase
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.domain.repository.IMovieRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/** @Author Kamal Nayan
Created on: 25/01/24
 *
 * searches for movie data
 **/
class SearchMovieUseCase @Inject constructor(private val iMovieRepository: IMovieRepository) :
    BaseUseCase<Pair<String, Int>, ApiResponse<SearchResponse>?>() {

    override suspend fun invoke(params: Pair<String, Int>?): ApiResponse<SearchResponse>? {
        val searchQuery = params?.first
        val pageNumber = params?.second

        if (validSearchQuery(searchQuery) && validPageNumber(pageNumber))
            return iMovieRepository.searchMovie(searchQuery, pageNumber)

        return null
    }

    @OptIn(ExperimentalContracts::class)
    private fun validPageNumber(pageNumber: Int?): Boolean {
        contract {
            returns() implies (pageNumber != null)
        }
        return pageNumber != null
    }

    @OptIn(ExperimentalContracts::class)
    private fun validSearchQuery(searchQuery: String?): Boolean {
        contract {
            returns() implies (searchQuery != null)
        }
        return searchQuery?.isBlank()?.not() ?: false
    }

}