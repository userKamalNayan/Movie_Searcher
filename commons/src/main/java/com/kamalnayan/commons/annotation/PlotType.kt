package com.kamalnayan.commons.annotation

import androidx.annotation.StringDef
import com.kamalnayan.commons.annotation.PlotType.Companion.PLOT_TYPE_FULL
import com.kamalnayan.commons.annotation.PlotType.Companion.PLOT_TYPE_SHORT

/** @Author Kamal Nayan
Created on: 27/01/24
 **/
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@StringDef(
    PLOT_TYPE_FULL,
    PLOT_TYPE_SHORT
)
annotation class PlotType() {
    companion object {
        const val PLOT_TYPE_FULL = "full"
        const val PLOT_TYPE_SHORT = "short"
    }
}
