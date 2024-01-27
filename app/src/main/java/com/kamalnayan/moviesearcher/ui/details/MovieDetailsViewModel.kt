package com.kamalnayan.moviesearcher.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kamalnayan.commons.annotation.PlotType
import com.kamalnayan.commons.base.BaseViewModel
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.kamalnayan.domain.usecase.GetMovieDetailsUseCase
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    BaseViewModel() {

    private val _movieDetails by lazy { MutableLiveData<MovieDetailsResponse>() }
    val movieDetails: LiveData<MovieDetailsResponse> by lazy { _movieDetails }


    fun getMovieDetails(id: String, @PlotType plotType: String) {
        viewModelScope.launch {
            _isFirstPageLoading.postValue(true)
            val response = getMovieDetailsUseCase(Pair(id, plotType))
            response?.suspendOnSuccess {
                handleSuccessResponse(this.data)
            }?.onFailure {
                _isFirstPageLoading.postValue(false)
                Log.d("details", "getMovieDetails: on failure $this")
            } ?: {
                _isFirstPageLoading.postValue(false)
                Log.d("details", "getMovieDetails: on null response")
            }

        }
    }

    private fun handleSuccessResponse(data: MovieDetailsResponse) {
        if (data.response == true) {
            Log.d("details", "handleSuccessResponse: SUCCESS ${data}")
            _movieDetails.postValue(data)
        }
        else {
            Log.d("details", "handleSuccessResponse: ERROR ${data.errorMessage}")
        }
        _isFirstPageLoading.postValue(false)
    }

}