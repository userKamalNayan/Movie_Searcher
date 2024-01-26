package com.kamalnayan.commons.base

import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
open class BaseRemoteDataSource {

    suspend inline fun <T> getResponse(
        crossinline request: suspend () -> ApiResponse<T>
    ): ApiResponse<T> {
        return withContext(Dispatchers.IO) { request.invoke() }
    }

}