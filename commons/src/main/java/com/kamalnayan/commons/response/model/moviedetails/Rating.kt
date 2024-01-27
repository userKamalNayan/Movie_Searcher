package com.kamalnayan.commons.response.model.moviedetails

import com.google.gson.annotations.SerializedName
import com.kamalnayan.commons.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String
) : BaseModel()