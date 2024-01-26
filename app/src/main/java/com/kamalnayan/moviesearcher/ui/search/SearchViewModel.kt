package com.kamalnayan.moviesearcher.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kamalnayan.commons.base.BaseViewModel
import com.kamalnayan.commons.response.SearchResponse
import com.kamalnayan.commons.response.model.SearchItem
import com.kamalnayan.commons.state.NetworkResult
import com.kamalnayan.domain.usecase.SearchMovieUseCase
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMovieUseCase: SearchMovieUseCase) :
    BaseViewModel() {

    private var pageNumber: Int = 0

    private var isLoading = false
    private var hasMoreData = true

    var canLoadMore: Boolean = !isLoading && hasMoreData

    private val _moviesList by lazy { MutableLiveData<NetworkResult<List<SearchItem>>>() }
    val moviesList: LiveData<NetworkResult<List<SearchItem>>> by lazy { _moviesList }

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error}


    fun searchMovie(searchQuery: String) {
        viewModelScope.launch {
            if (isLoading)
                return@launch
            isLoading = true
            pageNumber++
            if (pageNumber == 1) {
                _moviesList.postValue(NetworkResult.Loading())
            }
            val response = searchMovieUseCase(Pair(searchQuery, pageNumber))
            response?.suspendOnSuccess {
                handleSearchSuccess(this.data)
            }?.onFailure {
                _error.postValue(this)
                Log.d("api", "searchMovie: failure $this")
            }
        }
    }

    private fun handleSearchSuccess(data: SearchResponse) {
        if (data.response==false) {
            canLoadMore = false
            _error.postValue("no more data")
            return
        }

        if (pageNumber > 1) {
            val oldList = (_moviesList.value as NetworkResult.Success).data
            val newList = data.results
            val finalList = oldList + newList
            _moviesList.postValue(NetworkResult.Success(finalList))
        } else {
            _moviesList.postValue(NetworkResult.Success(data.results))
        }
        isLoading = false
    }
}