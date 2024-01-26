package com.kamalnayan.commons.extensions

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
            val layoutManager = this@loadMoreListener.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            val totalItems = this@loadMoreListener.childCount
            if ((lastVisibleItemPosition + threshold) >= totalItems) {
                loadMoreData.invoke()
            }
        }
    })
}