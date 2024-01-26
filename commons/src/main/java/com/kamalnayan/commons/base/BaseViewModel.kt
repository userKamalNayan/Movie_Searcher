package com.kamalnayan.commons.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
open class BaseViewModel : ViewModel() {
    protected val _isFirstPageLoading by lazy { MutableLiveData<Boolean>() }
    val isFirstPageLoading: LiveData<Boolean> by lazy { _isFirstPageLoading }

}