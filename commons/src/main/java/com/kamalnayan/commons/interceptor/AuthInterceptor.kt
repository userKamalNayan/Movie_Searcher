package com.kamalnayan.commons.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/** @Author Kamal Nayan
Created on: 26/01/24
 *
 * Intercepts request and adds `apiKey` query parameter
 *
 * NOT IN USE
 **/
@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val QUERY_API_KEY = "apikey"
        private const val QUERY_API_KEY_VALUE = "9cfa95f8"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().url.newBuilder()

        // updating url by adding `apiKey` query parameter
        val newUrl = requestBuilder.addQueryParameter(QUERY_API_KEY, QUERY_API_KEY_VALUE).build()

        // creating new request
        val newRequest = chain.request().newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}