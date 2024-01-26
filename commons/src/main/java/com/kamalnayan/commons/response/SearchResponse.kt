package com.kamalnayan.commons.response

import com.google.gson.annotations.SerializedName
import com.kamalnayan.commons.base.BaseResponse
import com.kamalnayan.commons.response.model.SearchItem
import kotlinx.parcelize.Parcelize

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
@Parcelize
data class SearchResponse(
    @SerializedName("Search")
    val results: List<SearchItem>,
) : BaseResponse()
