package com.kamalnayan.domain.repository

import com.kamalnayan.commons.response.SearchResponse
import com.skydoves.sandwich.ApiResponse

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
interface IMovieRepository {
    suspend fun searchMovie(query: String, page: Int): ApiResponse<SearchResponse>
}