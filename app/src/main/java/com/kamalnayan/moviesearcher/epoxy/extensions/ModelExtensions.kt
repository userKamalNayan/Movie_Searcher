package com.kamalnayan.moviesearcher.epoxy.extensions

import androidx.annotation.DimenRes
import com.kamalnayan.commons.extensions.setMarginFromDimen
import com.kamalnayan.moviesearcher.SpaceBindingModelBuilder
import com.kamalnayan.moviesearcher.databinding.ItemEpoxySpaceBinding

/** @Author Kamal Nayan
Created on: 27/01/24
 **/

fun SpaceBindingModelBuilder.margin(
    @DimenRes marginTop: Int = 0,
    @DimenRes marginBottom: Int = 0,
    @DimenRes marginStart: Int = 0,
    @DimenRes marginEnd: Int = 0
) {
    onBind { model, view, position ->
        (view.dataBinding as ItemEpoxySpaceBinding)?.let {
            it.parentContainer.setMarginFromDimen(
                marginTop = marginTop,
                marginBottom = marginBottom,
                marginStart = marginStart,
                marginEnd = marginEnd,
            )
        }
    }
}