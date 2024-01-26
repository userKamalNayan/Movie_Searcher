package com.kamalnayan.moviesearcher.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.kamalnayan.commons.response.model.SearchItem
import com.kamalnayan.moviesearcher.searchItem
import kotlin.math.log

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
                onBind { model, view, position ->
                    Log.d("api", "buildModels: on bind rating = ${it.rating} and imid= ${it.imdbID}")
                }
            }
        }
    }
}