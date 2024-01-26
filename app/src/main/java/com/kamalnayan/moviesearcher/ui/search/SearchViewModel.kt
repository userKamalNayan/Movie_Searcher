package com.kamalnayan.moviesearcher.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kamalnayan.commons.base.BaseViewModel
import com.kamalnayan.commons.modifier.SearchResultModifier
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.SearchItem
import com.kamalnayan.domain.usecase.SearchMovieUseCase
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMovieUseCase: SearchMovieUseCase) :
    BaseViewModel() {

    private var pageNumber: Int = 0
    private var isLoading = false

    /**
     * true if the last response was not null
     * because next page can have data in this case
     */
    private var hasMoreData = true

    /**
     * used to check more data can be loaded on a point of time or not
     */
    var canLoadMore: Boolean = !isLoading && hasMoreData

    /**
     * used to store the selected modifier.
     * e.g. Sort by year / Sort by rating
     */
    private var modifier: SearchResultModifier = SearchResultModifier.SortByDefault

    private var movieRawList: List<SearchItem>? = null

    private val _moviesModifiedList by lazy { MutableLiveData<List<SearchItem>>() }
    val moviesList: LiveData<List<SearchItem>> by lazy { _moviesModifiedList }

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error }


    /**
     * Searches movie using [searchQuery] by making
     * api call to remote.
     */
    fun searchMovie(searchQuery: String) {
        viewModelScope.launch {
            if (isLoading)
                return@launch
            isLoading = true
            pageNumber++

            val response = searchMovieUseCase(Pair(searchQuery, pageNumber))
            response?.suspendOnSuccess {
                handleSearchSuccess(this.data)
            }?.onFailure {
                _error.postValue(this)
                Log.d("api", "searchMovie: failure $this")
            }
        }
    }

    /**
     * handles the success case of network result for
     * search api
     */
    private suspend fun handleSearchSuccess(data: SearchResponse) {
        if (data.response == false) {// case: error occurred and response has no data
            canLoadMore = false
            _error.postValue("no more data")
            return
        }
        withContext(Dispatchers.Default) {
            if (pageNumber > 1) {// for next pages
                val oldList = movieRawList ?: emptyList()
                val newList = data.results.map { it.setRandomRating() } ?: emptyList()
                val updatedList = oldList + newList
                movieRawList = updatedList
                val finalList = getModifiedMoviesList(updatedList)
                _moviesModifiedList.postValue(finalList)
            } else { // for first page
                movieRawList = data.results
                val resultWithRandomRating = data.results.map { it.setRandomRating() }
                _moviesModifiedList.postValue(getModifiedMoviesList(resultWithRandomRating))
            }
            isLoading = false
        }
    }

    /**
     * applies modifier to movies list and updates the data accordingly
     */
    fun applyModifier(searchResultModifier: SearchResultModifier) {
        this.modifier = searchResultModifier
        _moviesModifiedList.postValue(getModifiedMoviesList(movieRawList ?: emptyList()))
    }

    /**
     * Returns modified list according to the [modifier] applied
     * by user.
     */
    private fun getModifiedMoviesList(list: List<SearchItem>): List<SearchItem> {
        return when (modifier) {
            SearchResultModifier.SortByDefault -> {
                return list
            }

            SearchResultModifier.SortByRating -> {
                list.sortedBy { it.rating }
            }

            SearchResultModifier.SortByYear -> {
                list.sortedBy { it.year }
            }
        }
    }

}