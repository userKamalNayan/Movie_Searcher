package com.kamalnayan.moviesearcher.ui.search

import com.kamalnayan.commons.response.model.SearchItem

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
sealed class SearchItemVO {
    data object AssistedSearch : SearchItemVO()
    data object Empty : SearchItemVO()
    data class SearchResult(val data: List<SearchItem>) : SearchItemVO()
    data class Error(val message: String) : SearchItemVO()

}