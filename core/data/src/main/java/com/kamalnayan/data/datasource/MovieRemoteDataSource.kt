package com.kamalnayan.data.datasource

import android.util.Log
import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.base.BaseRemoteDataSource
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.kamalnayan.data.api.AppApiService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
class MovieRemoteDataSource @Inject constructor(private val apiService: AppApiService) :
    BaseRemoteDataSource() {

    suspend fun searchMovie(searchQuery: String, pageNumber: Int): ApiResponse<SearchResponse> {
        return getResponse {
            apiService.searchMovie(searchQuery, pageNumber)
        }
    }

    suspend fun getMovieDetails(
        id: String,
        @PlotType plot: String
    ): ApiResponse<MovieDetailsResponse> {
        return getResponse {
            Log.d("details", "getMovieDetails: data source $id")
            apiService.getMovieDetails(id, plot)
        }
    }
}