package com.kamalnayan.data.repository

import com.kamalnayan.commons.response.SearchResponse
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
}