package com.kamalnayan.commons.extensions

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView

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