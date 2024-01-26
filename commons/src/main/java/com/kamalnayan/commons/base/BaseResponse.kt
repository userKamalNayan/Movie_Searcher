package com.kamalnayan.commons.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kamalnayan.commons.extensions.Empty
import kotlinx.parcelize.Parcelize

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
@Parcelize
open class BaseResponse : Parcelable {

    @SerializedName("Response")
    val response: Boolean? =null
}