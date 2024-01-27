package com.kamalnayan.commons.extensions

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.kamalnayan.commons.constants.Constants.DEFAULT_ANIMATION_DURATION
import com.kamalnayan.commons.utils.SafeClickListener

/** @Author Kamal Nayan
Created on: 26/01/24
 **/

/**
 * If the current position is 6 items less than the last position then the [loadMoreData] will be
 * invoked.
 */
fun EpoxyRecyclerView.loadMoreListener(threshold: Int = 6, loadMoreData: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition = when (this@loadMoreListener.layoutManager) {
                is GridLayoutManager -> {
                    (this@loadMoreListener.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                }

                is LinearLayoutManager -> {
                    (this@loadMoreListener.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                }

                else -> {
                    (this@loadMoreListener.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                }
            }
            val totalItems = this@loadMoreListener.adapter?.itemCount?:return

            if ((lastVisibleItemPosition + threshold) >= totalItems) {
                Log.d("load-more", "onScrolled:  $lastVisibleItemPosition and total = $totalItems")
                loadMoreData.invoke()
            }
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun View.setSafeClickListener(
    coolDownPeriod: Long = 1000, scaleX: Float = 0.97f,
    scaleY: Float = 0.97f,
    onClick: (View?) -> Unit
) {
    this.setOnClickListener(SafeClickListener(coolDownPeriod) {
        onClick(it)
    })

    this.setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            v.showTouchAnimation(scaleX, scaleY)
        } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
            v.showTouchReleaseAnimation()
        }
        return@setOnTouchListener false
    }
}

fun View.showTouchAnimation(scaleX: Float = 0.97f, scaleY: Float = 0.97f) {
    this.animate().scaleX(scaleX).setDuration(DEFAULT_ANIMATION_DURATION)
        .start()
    this.animate().scaleY(scaleY).setDuration(DEFAULT_ANIMATION_DURATION)
        .start()
}

fun View.showTouchReleaseAnimation() {
    this.animate().scaleX(1f).setDuration(DEFAULT_ANIMATION_DURATION).start()
    this.animate().scaleY(1f).setDuration(DEFAULT_ANIMATION_DURATION).start()
}


fun View.setMarginFromDimen(
    @DimenRes marginTop: Int=0,
    @DimenRes marginBottom: Int=0,
    @DimenRes marginStart: Int=0,
    @DimenRes marginEnd: Int = 0
) {
    val layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        with(context.resources)
        {
            bottomMargin =
                if (marginBottom == 0) this@setMarginFromDimen.marginBottom else getDimensionPixelOffset(
                    marginBottom
                )
            topMargin = if (marginTop == 0) this@setMarginFromDimen.marginTop else getDimensionPixelOffset(
                marginTop
            )
            leftMargin =
                if (marginStart == 0) this@setMarginFromDimen.marginStart else getDimensionPixelOffset(
                    marginStart
                )
            rightMargin =
                if (marginEnd == 0) this@setMarginFromDimen.marginEnd else getDimensionPixelOffset(
                    marginEnd
                )
        }
    }
    this.layoutParams=layoutParams
}
