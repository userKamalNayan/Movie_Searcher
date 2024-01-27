package com.kamalnayan.data.repository

import android.util.Log
import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.kamalnayan.data.datasource.MovieRemoteDataSource
import com.kamalnayan.domain.repository.IMovieRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
class MovieRepository @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    IMovieRepository {

    override suspend fun searchMovie(query: String, page: Int): ApiResponse<SearchResponse> {
        return movieRemoteDataSource.searchMovie(query, page)
    }

    override suspend fun getMovieDetails(
        id: String,
        @PlotType plot: String
    ): ApiResponse<MovieDetailsResponse> {
        Log.d("details", "getMovieDetails: repository $id")
        return movieRemoteDataSource.getMovieDetails(id, plot)
    }
}