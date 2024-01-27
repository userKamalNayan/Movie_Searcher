package com.kamalnayan.moviesearcher.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.kamalnayan.commons.response.model.moviedetails.MovieDetailsResponse
import com.kamalnayan.moviesearcher.epoxy.extensions.margin
import com.kamalnayan.moviesearcher.movieDetailsHeader
import com.kamalnayan.moviesearcher.plot
import com.kamalnayan.moviesearcher.space
import com.kamalnayan.moviesearcher.titleAndValue

/** @Author Kamal Nayan
Created on: 27/01/24
 **/
class DetailsController : AsyncEpoxyController() {

    var movieDetail: MovieDetailsResponse? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    /**
     * build whole view when data is available
     */
    override fun buildModels() {
        movieDetail?.let {
            buildDetailHeader(it)
            buildAttributes(it)
            buildPlot(it)
        }
    }

    /**
     * builds Poster and title of movie
     */
    private fun buildDetailHeader(detailsResponse: MovieDetailsResponse) {
        movieDetailsHeader {
            id(detailsResponse.imdbID)
            posterUrl(detailsResponse.poster)
            title(detailsResponse.title)
        }

        buildSpace("title_bottom")
    }

    /**
     * Builds release, genre, actors etc attributes view
     */
    private fun buildAttributes(detailsResponse: MovieDetailsResponse) {

        titleAndValue {
            id("released_on")
            title("Released On :")
            value(detailsResponse.released)
        }

        titleAndValue {
            id("genre")
            title("Genre :")
            value(detailsResponse.genre)
        }

        titleAndValue {
            id("actors")
            title("Actors :")
            value(detailsResponse.actors)
        }

        titleAndValue {
            id("language")
            title("Language")
            value(detailsResponse.language)
        }

        titleAndValue {
            id("country")
            title("Country")
            value(detailsResponse.country)
        }

    }

    /**
     * Build plot view
     */
    private fun buildPlot(detailsResponse: MovieDetailsResponse) {
        buildSpace("plot_top")

        plot {
            id("plot_details")
            plot(detailsResponse.plot)
        }
    }

    /**
     * Build space between items
     */
    private fun buildSpace(key: String) {
        space {
            id("space_$key")
            margin(com.kamalnayan.commons.R.dimen.item_margin_small)
        }
    }
}