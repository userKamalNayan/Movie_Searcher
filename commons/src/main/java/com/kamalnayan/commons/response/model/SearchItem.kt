package com.kamalnayan.commons.response.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.kamalnayan.commons.base.BaseModel
import kotlinx.parcelize.Parcelize
import kotlin.random.Random
import kotlin.random.nextInt

/** @Author Kamal Nayan
Created on: 26/01/24
 **/

@Parcelize
data class SearchItem(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String,
    @Transient
    var rating: Int,
) : BaseModel() {

    fun setRandomRating(): SearchItem {
        rating = Random.nextInt(1..10)
        Log.d("api", "random rating set for $title is $rating")
        return this
    }

}

