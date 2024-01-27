package com.kamalnayan.commons.annotation

import android.os.Parcelable
import androidx.annotation.IntDef
import com.kamalnayan.commons.annotation.ViewType.Companion.VIEW_TYPE_GRID
import com.kamalnayan.commons.annotation.ViewType.Companion.VIEW_TYPE_LIST
import kotlinx.parcelize.Parcelize

/** @Author Kamal Nayan
Created on: 26/01/24
 **/
@Target(AnnotationTarget.TYPE)
@IntDef(VIEW_TYPE_GRID, VIEW_TYPE_LIST)
annotation class ViewType() {
    companion object {
        const val VIEW_TYPE_GRID = 1
        const val VIEW_TYPE_LIST = 0
    }
}
