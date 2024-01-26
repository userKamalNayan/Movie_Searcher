package com.kamalnayan.moviesearcher.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.kamalnayan.commons.response.model.SearchItem
import com.kamalnayan.moviesearcher.searchItem

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
class SearchMovieController : AsyncEpoxyController() {

    var searchResults: List<SearchItem>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        searchResults?.forEach {
            searchItem {
                id(it.imdbID)
                posterUrl(it.poster)
                movieName(it.title)
            }
        }
    }
}