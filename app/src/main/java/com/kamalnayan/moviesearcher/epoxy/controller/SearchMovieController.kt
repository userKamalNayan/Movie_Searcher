package com.kamalnayan.moviesearcher.epoxy.controller

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.AsyncEpoxyController
import com.kamalnayan.commons.constants.Constants.SPAN_SIZE_FULL
import com.kamalnayan.commons.constants.Constants.SPAN_SIZE_HALF
import com.kamalnayan.commons.response.model.SearchItem
import com.kamalnayan.moviesearcher.LoaderBindingModel_
import com.kamalnayan.moviesearcher.loader
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

    var canLoadMore: Boolean = true
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        buildSearchItems()
        if (canLoadMore) {
            buildLoader()
        }
    }

    private fun buildLoader() {
        loader {
            id("loader")
        }
    }

    private fun buildSearchItems() {
        searchResults?.forEach {
            searchItem {
                id(it.imdbID)
                posterUrl(it.poster)
                movieName(it.title)
            }
        }
    }

    /**
     * Match width when showing [loader] otherwise follow
     * the span
     */
    override fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (this@SearchMovieController.adapter.getModelAtPosition(position) is LoaderBindingModel_) {
                    SPAN_SIZE_FULL
                } else {
                    SPAN_SIZE_HALF
                }
            }
        }
    }

}