package com.kamalnayan.domain.repository

import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.skydoves.sandwich.ApiResponse

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
interface IMovieRepository {
    suspend fun searchMovie(query: String, page: Int): ApiResponse<SearchResponse>
    suspend fun getMovieDetails(
        id: String,
        @PlotType plot: String
    ): ApiResponse<MovieDetailsResponse>
}