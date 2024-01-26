package com.kamalnayan.moviesearcher.epoxy.controller

import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.AsyncEpoxyController
import com.kamalnayan.commons.constants.Constants.SPAN_SIZE_FULL
import com.kamalnayan.commons.constants.Constants.SPAN_SIZE_HALF
import com.kamalnayan.moviesearcher.AssistedSearchBindingModel_
import com.kamalnayan.moviesearcher.ErrorViewBindingModel_
import com.kamalnayan.moviesearcher.LoaderBindingModel_
import com.kamalnayan.moviesearcher.assistedSearch
import com.kamalnayan.moviesearcher.errorView
import com.kamalnayan.moviesearcher.loader
import com.kamalnayan.moviesearcher.searchItem
import com.kamalnayan.moviesearcher.ui.search.SearchItemVO

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
class SearchMovieController : AsyncEpoxyController() {

    companion object {
        private val ASSISTED_SEARCH_QUERY= listOf("equalizer","the wolf","top")
    }

    var onAssistedSearchClick: ((String) -> Unit)? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var searchViewObject: SearchItemVO? = null
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
        when (searchViewObject) {
            SearchItemVO.AssistedSearch -> {
                buildAssistedSearchView(searchViewObject!!)
            }

            is SearchItemVO.Error -> {
                buildErrorView((searchViewObject as SearchItemVO.Error).message)
            }

            is SearchItemVO.SearchResult -> {
                buildSearchItems()
            }

            null -> {
                // do nothing
            }
        }
    }

    private fun buildAssistedSearchView(searchViewObject: SearchItemVO) {
        assistedSearch {
            id("assistedSearch")
            onClick { model, parentView, clickedView, position ->
                this@SearchMovieController.onAssistedSearchClick?.invoke(ASSISTED_SEARCH_QUERY.random())
            }
        }
    }

    private fun buildErrorView(message: String) {
        errorView {
            id("error")
            errorMessage(message)
            onClick { model, parentView, clickedView, position ->

            }
        }
    }

    private fun buildLoader() {
        loader {
            id("loader")
        }
    }

    private fun buildSearchItems() {
        (searchViewObject as SearchItemVO.SearchResult).data?.forEach {
            searchItem {
                id(it.imdbID)
                posterUrl(it.poster)
                movieName(it.title)
            }
        }
        if (canLoadMore) {
            buildLoader()
        }
    }

    /**
     * Match width when showing [loader] otherwise follow
     * the span
     */
    override fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (this@SearchMovieController.adapter.getModelAtPosition(position)) {
                    is LoaderBindingModel_ -> SPAN_SIZE_FULL
                    is AssistedSearchBindingModel_ -> SPAN_SIZE_FULL
                    is ErrorViewBindingModel_ -> SPAN_SIZE_FULL
                    else -> SPAN_SIZE_HALF
                }
            }
        }
    }

}