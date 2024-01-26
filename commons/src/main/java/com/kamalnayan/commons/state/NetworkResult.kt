package com.kamalnayan.commons.state

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
sealed class NetworkResult<out SomeResult> {

    data class Success<SomeResult>(val data: SomeResult) : NetworkResult<SomeResult>()
    class Loading<SomeResult> : NetworkResult<SomeResult>()
    data class Error<SomeResult>(val error: String) : NetworkResult<SomeResult>()
}