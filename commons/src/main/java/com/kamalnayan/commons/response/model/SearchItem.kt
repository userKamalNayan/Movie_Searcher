package com.kamalnayan.commons.response.model

import com.google.gson.annotations.SerializedName
import com.kamalnayan.commons.base.BaseModel
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

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
    val rating: Int = Random.nextInt(1, 6)
) : BaseModel()
