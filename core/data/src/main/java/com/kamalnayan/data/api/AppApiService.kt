package com.kamalnayan.data.api

import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
interface AppApiService {
    @GET("?apikey=9cfa95f8")
    suspend fun searchMovie(
        @Query("s") searchQuery: String,
        @Query("page") page: Int
    ): ApiResponse<SearchResponse>

    @GET("?apikey=9cfa95f8")
    suspend fun getMovieDetails(
        @Query("i") id: String,
        @Query("plot") plot: String
    ): ApiResponse<MovieDetailsResponse>
}