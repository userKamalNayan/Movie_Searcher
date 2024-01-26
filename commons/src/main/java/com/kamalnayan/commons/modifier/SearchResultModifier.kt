package com.kamalnayan.commons.modifier

import com.kamalnayan.commons.R

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
sealed class SearchResultModifier {

    abstract fun getTitleResourceId(): Int

    /**
     * data is sorted by [com.kamalnayan.commons.response.model.SearchItem.year]
     */
    data object SortByYear : SearchResultModifier() {
        override fun getTitleResourceId(): Int {
            return R.string.text_sort_by_year
        }

    }

    /**
     * data is sorted by [com.kamalnayan.commons.response.model.SearchItem.rating]
     */
    data object SortByRating : SearchResultModifier() {
        override fun getTitleResourceId(): Int {
            return R.string.text_sort_by_rating
        }
    }

    /**
     * in this case the data is presented as it was received
     */
    data object SortByDefault : SearchResultModifier() {
        override fun getTitleResourceId(): Int {
            return R.string.text_sort_by_default
        }
    }
}